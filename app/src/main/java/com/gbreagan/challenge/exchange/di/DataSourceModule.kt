package com.gbreagan.challenge.exchange.di

import android.content.Context
import com.gbreagan.challenge.exchange.data.datasource.local.ExchangeDatabase
import com.gbreagan.challenge.exchange.data.datasource.local.ExchangeLocalDataSource
import com.gbreagan.challenge.exchange.data.datasource.local.ExchangeLocalDataSourceImpl
import com.gbreagan.challenge.exchange.data.datasource.remote.ExchangeApiService
import com.gbreagan.challenge.exchange.data.datasource.remote.ExchangeRemoteDataSource
import com.gbreagan.challenge.exchange.data.datasource.remote.ExchangeRemoteDataSourceImpl
import com.gbreagan.challenge.exchange.data.datasource.remote.MockExchangeRemoteDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataSourceModule = module {
    single<ExchangeRemoteDataSource> {
        ExchangeRemoteDataSourceImpl(get<ExchangeApiService>())
    }
    single<ExchangeLocalDataSource> {
        ExchangeLocalDataSourceImpl(get<ExchangeDatabase>())
    }
}

val mockDataSourceModule = module {
    single<Context> {
        androidContext()
    }
    single {
        MockExchangeRemoteDataSource(get<Context>())
    }
}