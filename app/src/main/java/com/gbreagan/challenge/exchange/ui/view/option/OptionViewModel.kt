package com.gbreagan.challenge.exchange.ui.view.option

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gbreagan.challenge.exchange.core.result.ResultData
import com.gbreagan.challenge.exchange.domain.usecase.GetCurrenciesInfoUseCase
import kotlinx.coroutines.launch

class OptionViewModel(
    private val getCurrenciesInfoUseCase: GetCurrenciesInfoUseCase
) : ViewModel() {

    private val _uiState = mutableStateOf(OptionUiState())
    val uiState: State<OptionUiState> = _uiState

    fun loadOptions() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            when (val result = getCurrenciesInfoUseCase()) {
                is ResultData.Success -> {
                    _uiState.value = OptionUiState(options = result.data, filteredOptions = result.data)
                }
                is ResultData.Failure -> {
                    _uiState.value = OptionUiState(error = "Error al cargar datos")
                }
                is ResultData.Loading -> {
                    _uiState.value = OptionUiState(isLoading = true)
                }
            }
        }
    }

    fun onSearchQueryChanged(query: String) {
        val filtered = if (query.isBlank())
            _uiState.value.options
         else _uiState.value.options.filter {
            it.name.contains(query.trim(), ignoreCase = true) ||
                    it.code.contains(query.trim(), ignoreCase = true)
        }
        _uiState.value = _uiState.value.copy(
            searchQuery = query,
            filteredOptions = filtered
        )
    }
}