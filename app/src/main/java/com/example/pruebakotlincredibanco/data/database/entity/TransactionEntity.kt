package com.example.pruebakotlincredibanco.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pruebakotlincredibanco.domain.model.TransactionDomain

@Entity(tableName = "transactions")
data class TransactionsEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "receiptId")
    val receiptId: String,
    @ColumnInfo(name = "rrn")
    val rrn: String,
    @ColumnInfo(name = "statusCode")
    val statusCode: String,
    @ColumnInfo(name = "statusDescription")
    var statusDescription: String,
)

fun TransactionDomain.toDatabase() = TransactionsEntity(
    receiptId = receiptId,
    rrn = rrn,
    statusCode = statusCode,
    statusDescription = statusDescription
)
