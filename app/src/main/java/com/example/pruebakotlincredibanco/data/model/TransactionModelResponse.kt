package com.example.pruebacredibanco.dto.response

import com.example.pruebakotlincredibanco.data.database.entity.TransactionsEntity

data class TransactionModelResponse (
    var receiptId: String,
    var rrn: String,
    var statusCode: String,
    var statusDescription: String
)

 fun TransactionModelResponse.toEntity() = TransactionsEntity(
     receiptId = receiptId ?: "",
     rrn = rrn ?: "",
     statusCode = statusCode?: "",
     statusDescription = statusDescription?: ""

 )