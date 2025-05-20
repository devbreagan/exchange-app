package com.gbreagan.challenge.exchange.di

import com.gbreagan.challenge.exchange.domain.usecase.GetCurrenciesInfoUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetCurrenciesInfoUseCase(get(), get()) }
}