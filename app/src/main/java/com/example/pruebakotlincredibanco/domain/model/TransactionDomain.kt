package com.example.pruebakotlincredibanco.domain.model

import com.example.pruebacredibanco.dto.response.TransactionModelResponse
import com.example.pruebakotlincredibanco.data.database.entity.TransactionsEntity


data class TransactionDomain(
    val receiptId: String,
    val rrn: String,
    val statusCode: String,
    var statusDescription: String
)
fun TransactionModelResponse.toDomain() = TransactionDomain(receiptId,rrn, statusCode, statusDescription)
fun TransactionsEntity.toDomain() = TransactionDomain(receiptId,rrn, statusCode, statusDescription)




