package com.gbreagan.challenge.exchange.di

import android.content.Context
import androidx.room.Room
import com.gbreagan.challenge.exchange.data.datasource.local.ExchangeDatabase
import com.gbreagan.challenge.exchange.data.datasource.local.dao.OperationDao
import com.gbreagan.challenge.exchange.data.datasource.local.dao.SymbolDao
import org.koin.dsl.module

val dataBaseModule = module {
    single {
        Room.databaseBuilder(
            get<Context>(),
            ExchangeDatabase::class.java,
            "exchange.db"
        ).build()
    }

    single<SymbolDao> {
        get<ExchangeDatabase>().getSymbolDao()
    }
    single<OperationDao> {
        get<ExchangeDatabase>().getOperationDao()
    }
}
