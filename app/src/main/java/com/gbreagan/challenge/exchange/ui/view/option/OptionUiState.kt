package com.gbreagan.challenge.exchange.ui.view.option

import com.gbreagan.challenge.exchange.domain.model.CurrencyInfo

data class OptionUiState(
    val options: List<CurrencyInfo> = emptyList(),
    val isLoading: Boolean = false,
    val filteredOptions: List<CurrencyInfo> = emptyList(),
    val searchQuery: String = "",
    val error: String? = null
)