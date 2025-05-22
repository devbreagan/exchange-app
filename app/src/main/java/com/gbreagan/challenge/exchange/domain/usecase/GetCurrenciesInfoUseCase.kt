package com.gbreagan.challenge.exchange.domain.usecase

import com.gbreagan.challenge.exchange.core.dispatcher.DispatcherProvider
import com.gbreagan.challenge.exchange.core.result.ResultData
import com.gbreagan.challenge.exchange.domain.model.CurrencyInfo
import com.gbreagan.challenge.exchange.domain.repository.ExchangeRepository
import kotlinx.coroutines.withContext

class GetCurrenciesInfoUseCase(
    private val repository: ExchangeRepository,
    private val dispatcher: DispatcherProvider,
) {
    suspend operator fun invoke(): ResultData<List<CurrencyInfo>> = withContext(dispatcher.io) {
        repository.getCurrencyInfoList()//.flowOn(dispatcher.io)
    }
}