package com.gbreagan.challenge.exchange.domain.model

data class CurrencyInfo(
    val code: String,
    val name: String,
    val rate: Double
)

data class CurrencyRate(
    val code: String,
    val rate: String,
)