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
import com.gbreagan.challenge.exchange.domain.usecase.SaveOperationUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel(
    private val convertCurrencyUseCase: ConvertCurrencyUseCase,
    private val getCurrenciesInfoUseCase: GetCurrenciesInfoUseCase,
    private val saveOperationUseCase: SaveOperationUseCase,
) : ViewModel() {
    private val _uiState = mutableStateOf(HomeUiState())
    val uiState: MutableState<HomeUiState> = _uiState

    fun convertCurrency(from: String, to: String) {
        viewModelScope.launch {
            when(val result = getCurrenciesInfoUseCase()) {
                is ResultData.Failure -> {
                    _uiState.value = HomeUiState(error = result.exception.message.toString())
                }
                is ResultData.Loading -> {
                    _uiState.value = HomeUiState(isLoading = true)
                }
                is ResultData.Success -> {
                    val converted = convertCurrencyUseCase(
                        from = from,
                        to = to,
                        amount = _uiState.value.amountToSend.toDouble(),
                        rates = result.data
                    )
                    _uiState.value = _uiState.value.copy(
                        amountToReceive = converted.toTwoDecimalString(),
                        isLoading = false
                    )
                    if (converted != null) {
                        saveOperationUseCase(
                            amountSend = _uiState.value.amountToSend.toDouble(),
                            amountReceive = converted,
                            from = from,
                            to = to
                        )
                    }
                    _uiState.value = HomeUiState(
                        amountToSend = _uiState.value.amountToSend,
                        amountToReceive = converted.toTwoDecimalString(),
                    )
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