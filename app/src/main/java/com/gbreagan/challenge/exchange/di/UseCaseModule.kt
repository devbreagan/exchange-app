package com.gbreagan.challenge.exchange.di

import com.gbreagan.challenge.exchange.domain.usecase.ConvertCurrencyUseCase
import com.gbreagan.challenge.exchange.domain.usecase.GetCurrenciesInfoUseCase
import com.gbreagan.challenge.exchange.domain.usecase.GetOperationsUseCase
import com.gbreagan.challenge.exchange.domain.usecase.SaveOperationUseCase
import com.gbreagan.challenge.exchange.domain.usecase.SaveSymbolsUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetCurrenciesInfoUseCase(get(), get()) }
    factory { SaveSymbolsUseCase(get(), get()) }
    factory { ConvertCurrencyUseCase() }
    factory { SaveOperationUseCase(get(), get()) }
    factory { GetOperationsUseCase(get(), get()) }
}