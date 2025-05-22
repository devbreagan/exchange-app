package com.gbreagan.challenge.exchange.ui.view.home

data class HomeUiState(
    val amountToSend: String = "",
    val amountToReceive: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)