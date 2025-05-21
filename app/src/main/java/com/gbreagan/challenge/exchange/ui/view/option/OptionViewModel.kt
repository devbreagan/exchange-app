package com.gbreagan.challenge.exchange.ui.view.option

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gbreagan.challenge.exchange.core.result.ResultData
import com.gbreagan.challenge.exchange.domain.model.CurrencyInfo
import com.gbreagan.challenge.exchange.domain.usecase.GetCurrenciesInfoUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class OptionViewModel(
    private val getCurrenciesInfoUseCase: GetCurrenciesInfoUseCase
): ViewModel() {
    val uiState: StateFlow<ResultData<List<CurrencyInfo>>> =
        getCurrenciesInfoUseCase()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(10000),
                initialValue = ResultData.Loading
            )
}
//    val uiState = getCurrenciesInfoUseCase()
//        .map { result ->
//            when (result) {
//                is ResultData.Loading -> OptionUiState(isLoading = true)
//                is ResultData.Success -> OptionUiState(options = result.data)
//                is ResultData.Failure -> OptionUiState(error = "Error al cargar datos")
//            }
//        }
//        .stateIn(viewModelScope, SharingStarted.Eagerly, OptionUiState())