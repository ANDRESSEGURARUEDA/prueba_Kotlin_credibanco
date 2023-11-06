package com.example.pruebakotlincredibanco.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.pruebakotlincredibanco.data.database.entity.TransactionsEntity

@Dao
interface TransactionDao {

    @Insert
    suspend fun generateAuthorization(transactionsEntity: TransactionsEntity)

    @Query("SELECT * FROM transactions WHERE receiptId = :receiptId")
    suspend fun getTransactionByreceiptId(receiptId: String): List<TransactionsEntity>

    @Query("UPDATE transactions Set statusDescription = :statusDescription WHERE receiptId = :receiptId")
    suspend fun annulmentTransaction(statusDescription: String, receiptId: String)

    @Query("SELECT * FROM transactions")
    suspend fun getAllTransaction(): List<TransactionsEntity>


}