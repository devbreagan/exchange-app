package com.gbreagan.challenge.exchange.ui.view.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gbreagan.challenge.exchange.core.result.ResultData
import com.gbreagan.challenge.exchange.data.toTwoDecimalString
import com.gbreagan.challenge.exchange.domain.usecase.GetOperationsUseCase
import com.gbreagan.challenge.exchange.ui.view.option.OptionUiState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DetailViewModel(
    private val getOperationsUseCase: GetOperationsUseCase
) : ViewModel() {

    private val _uiState = mutableStateOf(DetailUiState())
    val uiState: State<DetailUiState> = _uiState

    fun loadOperations() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            when (val result = getOperationsUseCase()) {
                is ResultData.Success -> {
                    _uiState.value = DetailUiState(operations = result.data)
                }
                is ResultData.Failure -> {
                    _uiState.value = DetailUiState(error = "Error al cargar datos")
                }
                is ResultData.Loading -> {
                    _uiState.value = DetailUiState(isLoading = true)
                }
            }
        }
    }
}