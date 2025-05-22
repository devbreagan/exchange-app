package com.gbreagan.challenge.exchange.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gbreagan.challenge.exchange.data.datasource.local.entity.OperationEntity

@Dao
interface OperationDao {
    @Query("SELECT COUNT(*) FROM operation")
    fun count(): Int

    @Query("SELECT * FROM operation ORDER BY timestamp ASC")
    suspend fun selectAll(): List<OperationEntity>

    @Insert(OperationEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOne(operation: OperationEntity)
}