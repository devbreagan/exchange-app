package com.gbreagan.challenge.exchange.domain.usecase

import com.gbreagan.challenge.exchange.core.dispatcher.DispatcherProvider
import com.gbreagan.challenge.exchange.core.result.ResultData
import com.gbreagan.challenge.exchange.core.utils.currentLongTimestamp
import com.gbreagan.challenge.exchange.domain.model.Operation
import com.gbreagan.challenge.exchange.domain.repository.ExchangeRepository
import kotlinx.coroutines.withContext

class SaveOperationUseCase(
    private val repository: ExchangeRepository,
    private val dispatcher: DispatcherProvider
) {
    suspend operator fun invoke(
        amountSend: Double,
        amountReceive: Double,
        from: String,
        to: String
    ): ResultData<Boolean> = withContext(dispatcher.io) {
        val operation = Operation(amountSend, amountReceive, from, to, currentLongTimestamp())
        repository.saveOperation(operation)//.flowOn(dispatcher.io)
    }
}