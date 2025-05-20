package com.gbreagan.challenge.exchange.di

val appModules = listOf(
    networkModule,
//    mockDataSourceModule,
    dataBaseModule,
    dataSourceModule,
    dispatcherModule,
    repositoryModule,
    useCaseModule,
    viewModelModule,
)