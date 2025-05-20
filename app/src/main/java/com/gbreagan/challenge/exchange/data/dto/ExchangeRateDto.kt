package com.gbreagan.challenge.exchange.data.dto

import com.google.gson.annotations.SerializedName


data class ExchangeRateDto(
    @SerializedName("base")  val base: String,
    @SerializedName("date") val date: String,
    @SerializedName("rates") val rates: Map<String, Double>,
    @SerializedName("success") val success: Boolean,
    @SerializedName("timestamp")  val timestamp: Int
)