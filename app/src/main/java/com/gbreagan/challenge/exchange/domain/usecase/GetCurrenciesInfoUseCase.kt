package com.gbreagan.challenge.exchange.domain.usecase

import com.gbreagan.challenge.exchange.core.dispatcher.DispatcherProvider
import com.gbreagan.challenge.exchange.core.result.ResultData
import com.gbreagan.challenge.exchange.domain.model.CurrencyInfo
import com.gbreagan.challenge.exchange.domain.repository.ExchangeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class GetCurrenciesInfoUseCase(
    private val repository: ExchangeRepository,
    private val dispatcher: DispatcherProvider,
) {
    operator fun invoke(): Flow<ResultData<List<CurrencyInfo>>> {
        return repository.getCurrencyInfoList().flowOn(dispatcher.io)
    }
}