package com.gbreagan.challenge.exchange.di

import com.gbreagan.challenge.exchange.core.dispatcher.DefaultDispatcherProvider
import com.gbreagan.challenge.exchange.core.dispatcher.DispatcherProvider
import org.koin.dsl.module

val dispatcherModule = module {
    single<DispatcherProvider> { DefaultDispatcherProvider() }
}