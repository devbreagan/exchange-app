package com.gbreagan.challenge.exchange.data.datasource.local

import com.gbreagan.challenge.exchange.data.datasource.local.entity.SymbolsEntity
import kotlinx.coroutines.flow.Flow

interface ExchangeLocalDataSource {
    suspend fun getSymbols(): List<SymbolsEntity>
    suspend fun setSymbols(symbols: List<SymbolsEntity>)
}