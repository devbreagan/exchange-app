package com.gbreagan.challenge.exchange.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gbreagan.challenge.exchange.data.datasource.local.dao.OperationDao
import com.gbreagan.challenge.exchange.data.datasource.local.dao.SymbolDao
import com.gbreagan.challenge.exchange.data.datasource.local.entity.OperationEntity
import com.gbreagan.challenge.exchange.data.datasource.local.entity.SymbolsEntity

@Database(version = 1, entities = [SymbolsEntity::class, OperationEntity::class], exportSchema = false)
abstract class ExchangeDatabase: RoomDatabase() {
    abstract fun getSymbolDao(): SymbolDao
    abstract fun getOperationDao(): OperationDao
}