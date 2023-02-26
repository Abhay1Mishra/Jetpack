package com.example.newreto.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [Result::class], version = 1)
abstract class QuotesDataBase:RoomDatabase() {

    abstract fun getQuoteDao():QuotesDao

    companion object{

        private var Instance:QuotesDataBase?=null

        fun getDataBase(context: Context):QuotesDataBase{
            if (Instance ==null){
                synchronized(this){
                    Instance =Room.databaseBuilder(context,
                        QuotesDataBase::class.java
                        ,"quotes")
                        .build()
                }

            }
            return Instance!!
        }

    }
}