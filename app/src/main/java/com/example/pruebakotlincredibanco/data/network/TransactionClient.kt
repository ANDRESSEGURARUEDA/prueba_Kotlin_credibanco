package com.example.pruebacredibanco.network

import com.example.pruebacredibanco.dto.response.TransactionModelResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface TransactionClient {

    @POST("authorization")
    suspend fun createTransaction(@Body transactionModel: TransactionModel): Response<TransactionModelResponse>

    @POST("annulment")
    suspend fun annulmentTransaction(@Body requestAnulmentDTO: String): Call<TransactionModel>
}