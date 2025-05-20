package com.gbreagan.challenge.exchange

import android.app.Application
import com.gbreagan.challenge.exchange.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ExchangeApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ExchangeApp)
            modules(appModules)
        }
    }
}