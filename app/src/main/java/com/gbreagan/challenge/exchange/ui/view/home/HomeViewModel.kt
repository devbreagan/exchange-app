package com.gbreagan.challenge.exchange.ui.view.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gbreagan.challenge.exchange.core.result.ResultData
import com.gbreagan.challenge.exchange.data.isValidDecimal
import com.gbreagan.challenge.exchange.data.toTwoDecimalString
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
                            amountToReceive = convertCurrencyUseCase(
                                from = from,
                                to = to,
                                amount = _uiState.value.amountToSend.toDouble(),
                                rates = it.data
                            ).toTwoDecimalString(),
                        )
                    }
                    is ResultData.Failure -> {
                        _uiState.value = HomeUiState(error = it.exception.message.toString())
                    }
                    is ResultData.Loading -> {
                        _uiState.value = _uiState.value.copy(isLoading = true)
                    }
                }
            }
        }
    }

    fun onAmountSendChange(amount: String) {
        if (amount.isValidDecimal()) {
            _uiState.value = _uiState.value.copy(
                amountToSend = amount
            )
        }
    }

    fun onAmountReceiveChange(amount: String) {
        if (amount.isValidDecimal()) {
            _uiState.value = _uiState.value.copy(
                amountToReceive = amount
            )
        }
    }
}