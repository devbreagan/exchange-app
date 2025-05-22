package com.gbreagan.challenge.exchange.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gbreagan.challenge.exchange.data.datasource.local.entity.SymbolsEntity

@Dao
interface SymbolDao {
    @Query("SELECT COUNT(*) FROM symbol")
    fun count(): Int

    @Query("SELECT * FROM symbol ORDER BY code ASC")
    suspend fun selectAll(): List<SymbolsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(symbols: List<SymbolsEntity>)
}