package com.gbreagan.challenge.exchange.ui.view.home

import com.gbreagan.challenge.exchange.domain.model.CurrencyInfo

data class HomeUiState (
    val amountToSend: Double? = null,
    val amountToReceive: Double? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)