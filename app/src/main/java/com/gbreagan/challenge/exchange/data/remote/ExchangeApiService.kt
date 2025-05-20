package com.gbreagan.challenge.exchange.data.remote

import com.gbreagan.challenge.exchange.data.dto.ExchangeRateDto
import com.gbreagan.challenge.exchange.data.dto.SymbolsDto
import retrofit2.http.GET

interface ExchangeApiService {
    @GET("latest")
    suspend fun getRates(): ExchangeRateDto
    @GET("symbols")
    suspend fun getSymbols(): SymbolsDto
}