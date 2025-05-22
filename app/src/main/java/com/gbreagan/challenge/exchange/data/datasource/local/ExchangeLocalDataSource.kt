package com.gbreagan.challenge.exchange.data.datasource.local

import com.gbreagan.challenge.exchange.data.datasource.local.entity.OperationEntity
import com.gbreagan.challenge.exchange.data.datasource.local.entity.SymbolsEntity
import kotlinx.coroutines.flow.Flow

interface ExchangeLocalDataSource {
    suspend fun saveSymbols(symbolsEntity: List<SymbolsEntity>)
    suspend fun getSymbols(): List<SymbolsEntity>
    suspend fun saveOperation(operationEntity: OperationEntity)
    suspend fun getOperations() : List<OperationEntity>
}