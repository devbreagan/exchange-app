package com.gbreagan.challenge.exchange.ui.view.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gbreagan.challenge.exchange.core.result.ResultData
import com.gbreagan.challenge.exchange.data.toTwoDecimalString
import com.gbreagan.challenge.exchange.domain.usecase.GetOperationsUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class DetailViewModel(
    private val getOperationsUseCase: GetOperationsUseCase
) : ViewModel() {
    val uiState = getOperationsUseCase()
        .map { result ->
            when (result) {
                is ResultData.Loading -> DetailUiState(isLoading = true)
                is ResultData.Success -> DetailUiState(operations = result.data)
                is ResultData.Failure -> DetailUiState(error = "Error al cargar datos")
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = DetailUiState()
        )
}