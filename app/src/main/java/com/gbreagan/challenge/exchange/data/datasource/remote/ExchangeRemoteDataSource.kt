package com.gbreagan.challenge.exchange.data.datasource.remote

import com.gbreagan.challenge.exchange.data.datasource.remote.dto.ExchangeRateDto
import com.gbreagan.challenge.exchange.data.datasource.remote.dto.SymbolsDto

interface ExchangeRemoteDataSource {
    suspend fun getRates(): ExchangeRateDto
    suspend fun getSymbols(): SymbolsDto
}