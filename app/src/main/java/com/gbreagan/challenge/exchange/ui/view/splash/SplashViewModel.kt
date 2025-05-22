package com.gbreagan.challenge.exchange.ui.view.splash

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gbreagan.challenge.exchange.core.result.ResultData
import com.gbreagan.challenge.exchange.domain.usecase.SaveSymbolsUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel(
    private val saveSymbolsUseCase: SaveSymbolsUseCase
) : ViewModel() {
    private val _uiState = mutableStateOf<SplashUiState>(SplashUiState.Loading)
    val uiState: State<SplashUiState> = _uiState

    fun loadData() {
        viewModelScope.launch {
            delay(SplashConstants.SPLASH_DELAY_MS)
            when (val result = saveSymbolsUseCase()) {
                is ResultData.Success -> {
                    _uiState.value = SplashUiState.Success
                }
                is ResultData.Failure -> {
                    _uiState.value = SplashUiState.Error(result.exception.message.toString())
                }
                is ResultData.Loading -> SplashUiState.Loading
            }
        }
    }
}