package com.gbreagan.challenge.exchange.domain.model

//data class ExchangeRate (
//    val base: String,
//    val date: String,
//    val rates: List<Rate>,
//    val timestamp: Int
//)
data class CurrencyInfo(
    val code: String,
    val name: String,
    val rate: Double
)