package com.gbreagan.challenge.exchange.data.repository

import android.util.Log
import com.gbreagan.challenge.exchange.core.result.ResultData
import com.gbreagan.challenge.exchange.data.datasource.local.ExchangeLocalDataSource
import com.gbreagan.challenge.exchange.data.datasource.local.entity.SymbolsEntity
import com.gbreagan.challenge.exchange.data.datasource.remote.ExchangeRemoteDataSource
import com.gbreagan.challenge.exchange.data.datasource.remote.dto.ExchangeRateDto
import com.gbreagan.challenge.exchange.data.mapper.toCurrencyInfoList
import com.gbreagan.challenge.exchange.data.mapper.toEntity
import com.gbreagan.challenge.exchange.data.mapper.toModel
import com.gbreagan.challenge.exchange.domain.model.CurrencyInfo
import com.gbreagan.challenge.exchange.domain.model.Operation
import com.gbreagan.challenge.exchange.domain.repository.ExchangeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class ExchangeRepositoryImpl(
    private val remoteDataSource: ExchangeRemoteDataSource,
    private val localDataSource: ExchangeLocalDataSource,
) : ExchangeRepository {
    override fun getCurrencyInfoList(): Flow<ResultData<List<CurrencyInfo>>> = flow {
        emit(ResultData.Loading)
        try {
            val symbolsEntity: List<SymbolsEntity> = localDataSource.getSymbols()
            val ratesDto: ExchangeRateDto = remoteDataSource.getRates()
            val exchangeRates = symbolsEntity.toCurrencyInfoList(ratesDto)
            emit(ResultData.Success(exchangeRates))
        } catch (e: Exception) {
            emit(ResultData.Failure(e))
        }
    }

    override fun setSymbolsToLocal(): Flow<ResultData<Boolean>> = flow {
        emit(ResultData.Loading)
        try {
            val symbolsEntity: List<SymbolsEntity> =
                remoteDataSource.getSymbols().symbols.mapNotNull {
                    SymbolsEntity(code = it.key, name = it.value)
                }
            localDataSource.saveSymbols(symbolsEntity)
            emit(ResultData.Success(true))
        } catch (e: Exception) {
            emit(ResultData.Failure(e))
        }
    }.catch {
        emit(ResultData.Failure(it))
    }

    override fun saveOperation(operation: Operation): Flow<ResultData<Boolean>> = flow {
        emit(ResultData.Loading)
        try {
            localDataSource.saveOperation(operation.toEntity())
            emit(ResultData.Success(true))
        } catch (e: Exception) {
            emit(ResultData.Failure(e))
        }
    }.catch {
        emit(ResultData.Failure(it))
    }

    override fun getOperations(): Flow<ResultData<List<Operation>>> = flow {
        emit(ResultData.Loading)
        try {
            val operations = localDataSource.getOperations().map { it.toModel() }
            Log.d("getOperations", operations.toString())
            emit(ResultData.Success(operations))
        } catch (e: Exception) {
            emit(ResultData.Failure(e))
        }
    }.catch {
        emit(ResultData.Failure(it))
    }
}