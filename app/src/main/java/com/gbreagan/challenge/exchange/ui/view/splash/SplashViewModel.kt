package com.gbreagan.challenge.exchange.ui.view.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gbreagan.challenge.exchange.core.result.ResultData
import com.gbreagan.challenge.exchange.domain.usecase.SaveSymbolsUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SplashViewModel(
    private val saveSymbolsUseCase: SaveSymbolsUseCase
): ViewModel() {
    private val _uiState = MutableStateFlow<SplashUiState>(SplashUiState.Loading)
    val uiState: StateFlow<SplashUiState> = _uiState.asStateFlow()
    fun loadData() {
        viewModelScope.launch {
            delay(SplashConstants.SPLASH_DELAY_MS)
            _uiState.value = SplashUiState.Loading
            saveSymbolsUseCase().collect {
                when (it) {
                    is ResultData.Success -> {
                        //delay(SplashConstants.SPLASH_DELAY_MS)
                        _uiState.value = SplashUiState.Success
                    }
                    is ResultData.Failure -> {
                        _uiState.value = SplashUiState.Error(it.exception.message.toString())
                    }
                    is ResultData.Loading -> SplashUiState.Loading
                }
            }
        }
    }
}