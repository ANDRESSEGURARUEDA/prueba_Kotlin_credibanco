package com.example.arquitecturamvvm.data.network


import com.example.pruebacredibanco.dto.response.TransactionModelResponse
import com.example.pruebacredibanco.network.TransactionClient
import com.example.pruebacredibanco.network.TransactionModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TransactionService @Inject constructor(private val api: TransactionClient) {

    suspend fun authorizarion(transactionModel: TransactionModel): TransactionModelResponse?{
        return withContext(Dispatchers.IO){
            val response = api.createTransaction(transactionModel)
            response.body()
        }
    }

}