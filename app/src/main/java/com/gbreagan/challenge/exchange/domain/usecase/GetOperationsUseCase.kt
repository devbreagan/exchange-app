package com.gbreagan.challenge.exchange.domain.usecase

import com.gbreagan.challenge.exchange.core.dispatcher.DispatcherProvider
import com.gbreagan.challenge.exchange.core.result.ResultData
import com.gbreagan.challenge.exchange.domain.model.Operation
import com.gbreagan.challenge.exchange.domain.repository.ExchangeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class GetOperationsUseCase(
    private val repository: ExchangeRepository,
    private val dispatcher: DispatcherProvider
) {
    operator fun invoke(): Flow<ResultData<List<Operation>>> {
        return repository.getOperations().flowOn(dispatcher.io)
    }
}