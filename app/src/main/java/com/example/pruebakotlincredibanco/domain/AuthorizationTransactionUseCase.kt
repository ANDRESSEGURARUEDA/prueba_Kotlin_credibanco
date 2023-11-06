package com.example.pruebakotlincredibanco.domain


import com.example.arquitecturamvvm.data.TransactionRepository
import com.example.pruebacredibanco.network.TransactionModel
import com.example.pruebakotlincredibanco.data.database.entity.toDatabase
import com.example.pruebakotlincredibanco.domain.model.TransactionDomain
import javax.inject.Inject


class AuthorizationTransactionUseCase @Inject constructor(
    private val repository: TransactionRepository
) {
    suspend operator fun invoke(transactionModel: TransactionModel): TransactionDomain? {
        val transaction = repository.authorizarionFromApi(transactionModel)
        if (transaction != null) {
            repository.insertTransaction(transaction?.toDatabase())
            transaction
            return transaction
        }
        return null
      }
}


