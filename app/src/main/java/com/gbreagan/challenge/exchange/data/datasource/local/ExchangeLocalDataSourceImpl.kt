package com.gbreagan.challenge.exchange.data.datasource.local

import com.gbreagan.challenge.exchange.data.datasource.local.entity.SymbolsEntity

class ExchangeLocalDataSourceImpl(
    private val database: ExchangeDatabase
) : ExchangeLocalDataSource {
    override suspend fun getSymbols(): List<SymbolsEntity> {
        return database.getSymbolDao().selectAll()
    }

    override suspend fun setSymbols(symbols: List<SymbolsEntity>) {
        return database.getSymbolDao().insertAll(symbols)
    }
}