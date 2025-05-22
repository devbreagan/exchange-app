package com.gbreagan.challenge.exchange.domain.repository

import com.gbreagan.challenge.exchange.core.result.ResultData
import com.gbreagan.challenge.exchange.domain.model.CurrencyInfo
import com.gbreagan.challenge.exchange.domain.model.Operation

interface ExchangeRepository {
    suspend fun getCurrencyInfoList(): ResultData<List<CurrencyInfo>>
    suspend fun setSymbolsToLocal(): ResultData<Boolean>
    suspend fun saveOperation(operation: Operation): ResultData<Boolean>
    suspend fun getOperations(): ResultData<List<Operation>>
}