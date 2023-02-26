package com.example.newreto.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface QuotesDao {
    @Insert
    suspend fun addQuotes(quotes: List<Result>)

    @Query("Select * From quotes")
    suspend fun getQuotes():List<Result>
}