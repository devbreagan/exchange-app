package com.gbreagan.challenge.exchange.domain.model

data class Operation(
    val amountSend: Double,
    val amountReceive: Double,
    val from: String,
    val to: String,
    val timestamp: Long
)