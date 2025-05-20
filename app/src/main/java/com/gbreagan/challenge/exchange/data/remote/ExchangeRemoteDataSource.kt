package com.gbreagan.challenge.exchange.data.remote

import com.gbreagan.challenge.exchange.data.dto.ExchangeRateDto
import com.gbreagan.challenge.exchange.data.dto.SymbolsDto

class ExchangeRemoteDataSource(
    private val exchangeApiService: ExchangeApiService
): ExchangeDataSource {
    override suspend fun getRates(): ExchangeRateDto =
        exchangeApiService.getRates()

    override suspend fun getSymbols(): SymbolsDto =
        exchangeApiService.getSymbols()
}