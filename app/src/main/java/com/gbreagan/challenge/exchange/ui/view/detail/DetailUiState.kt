package com.gbreagan.challenge.exchange.ui.view.detail

import com.gbreagan.challenge.exchange.domain.model.Operation

data class DetailUiState(
    val operations: List<Operation> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)