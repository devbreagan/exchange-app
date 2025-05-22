package com.gbreagan.challenge.exchange.domain.usecase

import com.gbreagan.challenge.exchange.core.dispatcher.DispatcherProvider
import com.gbreagan.challenge.exchange.core.result.ResultData
import com.gbreagan.challenge.exchange.domain.repository.ExchangeRepository
import kotlinx.coroutines.withContext

class SaveSymbolsUseCase(
    private val repository: ExchangeRepository,
    private val dispatcher: DispatcherProvider,
) {
    suspend operator fun invoke(): ResultData<Boolean> = withContext(dispatcher.io) {
        repository.setSymbolsToLocal()//.flowOn(dispatcher.io)
    }
}