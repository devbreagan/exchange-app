package com.gbreagan.challenge.exchange.di

import com.gbreagan.challenge.exchange.data.datasource.local.ExchangeLocalDataSource
import com.gbreagan.challenge.exchange.data.datasource.remote.ExchangeRemoteDataSource
import com.gbreagan.challenge.exchange.data.repository.ExchangeRepositoryImpl
import com.gbreagan.challenge.exchange.domain.repository.ExchangeRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<ExchangeRepository> {
        ExchangeRepositoryImpl(
            get<ExchangeRemoteDataSource>(),
            get<ExchangeLocalDataSource>()
        )
    }
}