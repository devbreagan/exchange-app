package com.gbreagan.challenge.exchange.data.remote

import com.gbreagan.challenge.exchange.data.dto.ExchangeRateDto
import com.gbreagan.challenge.exchange.data.dto.SymbolsDto

interface ExchangeDataSource {
    suspend fun getRates(): ExchangeRateDto
    suspend fun getSymbols(): SymbolsDto
}