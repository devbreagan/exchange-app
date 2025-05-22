package com.gbreagan.challenge.exchange.data.datasource.remote

import android.content.Context
import com.gbreagan.challenge.exchange.data.datasource.remote.dto.ExchangeRateDto
import com.gbreagan.challenge.exchange.data.datasource.remote.dto.SymbolsDto
import com.gbreagan.challenge.exchange.data.loadJsonFromAsset
import com.google.gson.Gson
import okio.IOException

class MockExchangeRemoteDataSource(
    private val context: Context
) : ExchangeRemoteDataSource {
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