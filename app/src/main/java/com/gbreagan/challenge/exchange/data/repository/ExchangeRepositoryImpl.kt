package com.gbreagan.challenge.exchange.data.repository

import com.gbreagan.challenge.exchange.core.result.ResultData
import com.gbreagan.challenge.exchange.data.remote.ExchangeDataSource
import com.gbreagan.challenge.exchange.data.remote.ExchangeRemoteDataSource
import com.gbreagan.challenge.exchange.data.remote.MockExchangeRemoteDataSource
import com.gbreagan.challenge.exchange.domain.model.CurrencyInfo
import com.gbreagan.challenge.exchange.domain.repository.ExchangeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ExchangeRepositoryImpl(
    private val dataSource: ExchangeDataSource,
): ExchangeRepository {
    override fun getCurrencyInfoList(): Flow<ResultData<List<CurrencyInfo>>> = flow {
        try {
            val symbolsDto = dataSource.getSymbols()
            //val ratesDto = dataSource.getRates()
            val exchangeRates = symbolsDto.symbols.mapNotNull { (code, name) ->
//                ratesDto.rates[code]?.let { rate ->
                    CurrencyInfo(code, name, 0.0)
//                }
            }
            emit(ResultData.Success(exchangeRates))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(ResultData.Failure(e))
        }
    }
}