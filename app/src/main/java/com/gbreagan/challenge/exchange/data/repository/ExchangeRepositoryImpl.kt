package com.gbreagan.challenge.exchange.data.repository

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
            ResultData.Failure(Throwable("Unknown error"))
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
            ResultData.Failure(Throwable("Database error"))
        }
    }


    override suspend fun saveOperation(operation: Operation): ResultData<Boolean> {
        return try {
            localDataSource.saveOperation(operation.toEntity())
            ResultData.Success(true)
        } catch (e: Exception) {
            ResultData.Failure(Throwable("Database error"))
        }
    }

    override suspend fun getOperations(): ResultData<List<Operation>> {
        return try {
            val operations = localDataSource.getOperations().map { it.toModel() }
            ResultData.Success(operations)
        } catch (e: Exception) {
            ResultData.Failure(Throwable("Database error"))
        }
    }
}