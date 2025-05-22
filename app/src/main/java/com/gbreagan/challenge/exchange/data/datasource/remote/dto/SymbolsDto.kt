package com.gbreagan.challenge.exchange.data.datasource.remote.dto

import com.google.gson.annotations.SerializedName

data class SymbolsDto(
    @SerializedName("success") val success: Boolean,
    @SerializedName("symbols") val symbols: Map<String, String>
)