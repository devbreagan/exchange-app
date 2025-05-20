package com.gbreagan.challenge.exchange.di

import com.gbreagan.challenge.exchange.ui.view.home.HomeViewModel
import com.gbreagan.challenge.exchange.ui.view.option.OptionViewModel
import com.gbreagan.challenge.exchange.ui.view.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SplashViewModel(get()) }
    viewModel { HomeViewModel() }
    viewModel { OptionViewModel(get()) }
}