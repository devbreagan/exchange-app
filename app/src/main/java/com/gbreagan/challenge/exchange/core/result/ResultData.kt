package com.gbreagan.challenge.exchange.core.result

sealed class ResultData<out T> {
    data class Success<T>(val data: T) : ResultData<T>()
    data class Failure(val exception: Throwable) : ResultData<Nothing>()
    data object Loading : ResultData<Nothing>()
}