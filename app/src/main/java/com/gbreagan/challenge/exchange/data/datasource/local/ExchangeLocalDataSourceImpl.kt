package com.gbreagan.challenge.exchange.data.datasource.local

import com.gbreagan.challenge.exchange.data.datasource.local.entity.OperationEntity
import com.gbreagan.challenge.exchange.data.datasource.local.entity.SymbolsEntity

class ExchangeLocalDataSourceImpl(
    private val database: ExchangeDatabase
) : ExchangeLocalDataSource {

    override suspend fun saveSymbols(symbolsEntity: List<SymbolsEntity>) {
        database.getSymbolDao().insertAll(symbolsEntity)
    }

    override suspend fun getSymbols(): List<SymbolsEntity> {
        return database.getSymbolDao().selectAll()
    }

    override suspend fun saveOperation(operationEntity: OperationEntity) {
        database.getOperationDao().insertOne(operationEntity)
    }

    override suspend fun getOperations(): List<OperationEntity> {
        return database.getOperationDao().selectAll()
    }

}