package com.gbreagan.challenge.exchange.domain.repository

import com.gbreagan.challenge.exchange.core.result.ResultData
import com.gbreagan.challenge.exchange.domain.model.CurrencyInfo
import kotlinx.coroutines.flow.Flow

interface ExchangeRepository {
    fun getCurrencyInfoList(): Flow<ResultData<List<CurrencyInfo>>>
}