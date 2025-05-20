package com.gbreagan.challenge.exchange.data.remote

import android.content.Context
import com.gbreagan.challenge.exchange.data.dto.ExchangeRateDto
import com.gbreagan.challenge.exchange.data.dto.SymbolsDto
import com.gbreagan.challenge.exchange.data.loadJsonFromAsset
import com.google.gson.Gson
import okio.IOException

class MockExchangeRemoteDataSource(
    private val context: Context
): ExchangeDataSource {
    override suspend fun getRates(): ExchangeRateDto {
        val json = context.loadJsonFromAsset("rates.json")
            ?: throw IOException("JSON de mock no encontrado")
        return Gson().fromJson(json, ExchangeRateDto::class.java)
    }

    override suspend fun getSymbols(): SymbolsDto {
        val json = context.loadJsonFromAsset("symbols.json")
            ?: throw IOException("JSON de mock no encontrado")
        return Gson().fromJson(json, SymbolsDto::class.java)
    }
}