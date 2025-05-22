package com.gbreagan.challenge.exchange.ui.view.splash

sealed class SplashUiState {
    data object Loading : SplashUiState()
    data object Success : SplashUiState()
    data class Error(val message: String) : SplashUiState()
}