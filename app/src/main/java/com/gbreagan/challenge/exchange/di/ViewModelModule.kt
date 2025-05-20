package com.gbreagan.challenge.exchange.di

import com.gbreagan.challenge.exchange.ui.view.option.OptionViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { OptionViewModel(get()) }
}