package com.gbreagan.challenge.exchange.ui.view.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gbreagan.challenge.exchange.core.result.ResultData
import com.gbreagan.challenge.exchange.data.orZero
import com.gbreagan.challenge.exchange.domain.usecase.ConvertCurrencyUseCase
import com.gbreagan.challenge.exchange.domain.usecase.GetCurrenciesInfoUseCase
import kotlinx.coroutines.launch

class HomeViewModel(
    private val convertCurrencyUseCase: ConvertCurrencyUseCase,
    private val getCurrenciesInfoUseCase: GetCurrenciesInfoUseCase
) : ViewModel() {
    private val _uiState = mutableStateOf(HomeUiState())
    val uiState: MutableState<HomeUiState> = _uiState

    fun convertCurrency(from: String, to: String) {
        viewModelScope.launch {
            getCurrenciesInfoUseCase().collect {
                when (it) {
                    is ResultData.Success -> {
                        _uiState.value = HomeUiState(
                            amountToSend = _uiState.value.amountToSend,
                            amountToReceive = convertCurrencyUseCase(from, to, _uiState.value.amountToSend.orZero(), it.data),
                        )
                    }
                    is ResultData.Failure -> {}
                    is ResultData.Loading -> {}
                }
            }
        }
    }

    fun onAmountSendChange(amount: String) {
        _uiState.value = _uiState.value.copy(
            amountToSend = amount.toDouble()
        )
    }
}