package com.example.arquitecturamvvm.data

import com.example.arquitecturamvvm.data.network.TransactionService
import com.example.pruebacredibanco.dto.response.TransactionModelResponse
import com.example.pruebacredibanco.network.TransactionModel
import com.example.pruebakotlincredibanco.data.database.dao.TransactionDao
import com.example.pruebakotlincredibanco.data.database.entity.TransactionsEntity
import com.example.pruebakotlincredibanco.domain.model.TransactionDomain
import com.example.pruebakotlincredibanco.domain.model.toDomain
import javax.inject.Inject

class TransactionRepository @Inject constructor(
    private val api : TransactionService, private val transactionDao: TransactionDao
){

    suspend fun authorizarionFromApi(transactionModel: TransactionModel): TransactionDomain? {
        val response: TransactionModelResponse? = api.authorizarion(transactionModel)
        return response?.toDomain()
    }

    suspend fun getTransactionByreceiptId(receiptId: String):List<TransactionDomain>{
        val response: List<TransactionsEntity> = transactionDao.getTransactionByreceiptId(receiptId)
        return response.map { it.toDomain() }
    }

    suspend fun updateTransaction(transactionsEntity: TransactionsEntity?){
        if (transactionsEntity != null) {
            transactionDao.annulmentTransaction(transactionsEntity.statusDescription, transactionsEntity.receiptId)
        }
    }

    suspend fun insertTransaction(transactionsEntity: TransactionsEntity?) {
        if (transactionsEntity != null) {
            transactionDao.generateAuthorization(transactionsEntity)
        }
    }

    suspend fun getAllTransactionFromDatabase():List<TransactionDomain>{
        val response: List<TransactionsEntity> = transactionDao.getAllTransaction()
        return response.map { it.toDomain() }
    }

}