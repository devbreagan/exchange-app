package com.gbreagan.challenge.exchange.di

import com.gbreagan.challenge.exchange.data.repository.ExchangeRepositoryImpl
import com.gbreagan.challenge.exchange.domain.repository.ExchangeRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<ExchangeRepository> { ExchangeRepositoryImpl(get()) }
}