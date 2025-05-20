package com.gbreagan.challenge.exchange.data.datasource.remote

import com.gbreagan.challenge.exchange.data.datasource.remote.dto.ExchangeRateDto
import com.gbreagan.challenge.exchange.data.datasource.remote.dto.SymbolsDto
import retrofit2.http.GET

interface ExchangeApiService {
    @GET("latest")
    suspend fun getRates(): ExchangeRateDto
    @GET("symbols")
    suspend fun getSymbols(): SymbolsDto
}