package com.gbreagan.challenge.exchange.ui.view.option

import com.gbreagan.challenge.exchange.domain.model.CurrencyInfo

data class OptionUiState(
    val recipes: List<CurrencyInfo> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)