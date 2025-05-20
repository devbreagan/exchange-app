package com.gbreagan.challenge.exchange.data.datasource.remote

import com.gbreagan.challenge.exchange.data.datasource.remote.dto.ExchangeRateDto
import com.gbreagan.challenge.exchange.data.datasource.remote.dto.SymbolsDto

class ExchangeRemoteDataSourceImpl(
    private val exchangeApiService: ExchangeApiService
): ExchangeRemoteDataSource {
    override suspend fun getRates(): ExchangeRateDto =
        exchangeApiService.getRates()

    override suspend fun getSymbols(): SymbolsDto =
        exchangeApiService.getSymbols()
}