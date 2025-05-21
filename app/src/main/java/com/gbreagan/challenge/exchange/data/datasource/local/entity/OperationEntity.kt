package com.gbreagan.challenge.exchange.data.datasource.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "operation")
data class OperationEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val amountSend: Double,
    val amountReceive: Double,
    val from: String,
    val to: String,
    val timestamp: Int
)