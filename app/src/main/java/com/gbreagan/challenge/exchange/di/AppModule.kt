package com.gbreagan.challenge.exchange.di

val appModules = listOf(
    networkModule,
//    mockDataSourceModule,
    dataSourceModule,
    dispatcherModule,
    repositoryModule,
    useCaseModule,
    viewModelModule,
)