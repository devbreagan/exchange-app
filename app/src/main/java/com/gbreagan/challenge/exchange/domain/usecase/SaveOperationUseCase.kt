package com.gbreagan.challenge.exchange.domain.usecase

import com.gbreagan.challenge.exchange.core.dispatcher.DispatcherProvider
import com.gbreagan.challenge.exchange.core.result.ResultData
import com.gbreagan.challenge.exchange.core.utils.currentLongTimestamp
import com.gbreagan.challenge.exchange.core.utils.currentTimestamp
import com.gbreagan.challenge.exchange.domain.model.Operation
import com.gbreagan.challenge.exchange.domain.repository.ExchangeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class SaveOperationUseCase(
    private val repository: ExchangeRepository,
    private val dispatcher: DispatcherProvider
) {
    operator fun invoke(
        amountSend: Double,
        amountReceive: Double,
        from: String,
        to: String
    ): Flow<ResultData<Boolean>> {
        val operation = Operation(amountSend, amountReceive, from, to, currentLongTimestamp())
        return repository.saveOperation(operation).flowOn(dispatcher.io)
    }
}