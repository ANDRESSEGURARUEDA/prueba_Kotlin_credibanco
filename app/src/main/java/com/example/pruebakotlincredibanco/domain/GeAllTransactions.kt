package com.example.pruebakotlincredibanco.domain

import com.example.arquitecturamvvm.data.TransactionRepository
import com.example.pruebakotlincredibanco.domain.model.TransactionDomain
import javax.inject.Inject

class GeAllTransactions  @Inject constructor(private val repository: TransactionRepository)  {

    suspend operator fun invoke(): List<TransactionDomain>? {
        val transactions = repository.getAllTransactionFromDatabase()
        if (!transactions.isNullOrEmpty()) {
            return transactions
        }
        return null
    }
}