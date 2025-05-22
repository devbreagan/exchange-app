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
    override suspend fun getCurrencyInfoList(): ResultData<List<CurrencyInfo>> {
        return try {
            val symbolsEntity: List<SymbolsEntity> = localDataSource.getSymbols()
            val ratesDto: ExchangeRateDto = remoteDataSource.getRates()
            val exchangeRates = symbolsEntity.toCurrencyInfoList(ratesDto)
            ResultData.Success(exchangeRates)
        } catch (e: Exception) {
            ResultData.Failure(e)
        }
    }

    override suspend fun setSymbolsToLocal(): ResultData<Boolean> {
        return try {
            val symbolsEntity: List<SymbolsEntity> =
                remoteDataSource.getSymbols().symbols.mapNotNull {
                    SymbolsEntity(code = it.key, name = it.value)
                }
            localDataSource.saveSymbols(symbolsEntity)
            ResultData.Success(true)
        } catch (e: Exception) {
            ResultData.Failure(e)
        }
    }


    override suspend fun saveOperation(operation: Operation): ResultData<Boolean> {
        return try {
            localDataSource.saveOperation(operation.toEntity())
            ResultData.Success(true)
        } catch (e: Exception) {
            ResultData.Failure(e)
        }
    }

    override suspend fun getOperations(): ResultData<List<Operation>> {
        return try {
            val operations = localDataSource.getOperations().map { it.toModel() }
            ResultData.Success(operations)
        } catch (e: Exception) {
            ResultData.Failure(e)
        }
    }
}