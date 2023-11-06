package com.example.pruebakotlincredibanco.domain

import com.example.arquitecturamvvm.data.TransactionRepository
import com.example.pruebakotlincredibanco.data.database.entity.toDatabase
import com.example.pruebakotlincredibanco.domain.model.TransactionDomain
import javax.inject.Inject

class GetTransactionUseCase @Inject constructor(private val repository: TransactionRepository) {

    suspend operator fun invoke(receiptId: String): List<TransactionDomain>? {
        val transactions = repository.getTransactionByreceiptId(receiptId)
        if (!transactions.isNullOrEmpty()) {
            return transactions
        }
        return null
    }


    suspend  fun annulmenteTransaction(transactionsEntity: TransactionDomain): Any {
          return  repository.updateTransaction(transactionsEntity?.toDatabase())
    }

}
