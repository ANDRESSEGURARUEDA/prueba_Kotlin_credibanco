package com.example.pruebakotlincredibanco.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pruebakotlincredibanco.data.database.dao.TransactionDao
import com.example.pruebakotlincredibanco.data.database.entity.TransactionsEntity

@Database(entities = [TransactionsEntity::class], version = 1, exportSchema = false)
abstract class TransactionDatabase: RoomDatabase() {


    abstract fun getTransactionDao(): TransactionDao
}