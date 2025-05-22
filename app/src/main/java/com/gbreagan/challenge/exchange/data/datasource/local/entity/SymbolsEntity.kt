package com.gbreagan.challenge.exchange.data.datasource.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "symbol")
data class SymbolsEntity(
    @PrimaryKey
    val code: String,
    val name: String,
)