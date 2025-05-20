package com.gbreagan.challenge.exchange.di

import android.content.Context
import com.gbreagan.challenge.exchange.data.remote.ExchangeApiService
import com.gbreagan.challenge.exchange.data.remote.ExchangeDataSource
import com.gbreagan.challenge.exchange.data.remote.ExchangeRemoteDataSource
import com.gbreagan.challenge.exchange.data.remote.MockExchangeRemoteDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataSourceModule = module {
    single<ExchangeDataSource> {
        ExchangeRemoteDataSource(get<ExchangeApiService>())
    }
}

val mockDataSourceModule = module {
    single<Context> {
        androidContext()
    }
    single<ExchangeDataSource> {
        MockExchangeRemoteDataSource(get<Context>())
    }
}