package com.example.pruebakotlincredibanco.di


import com.example.pruebacredibanco.network.TransactionClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetwokModule {

    //Se utiliza  Interceptor para mostrar los resultados de la peticion
    private val loggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private val client = OkHttpClient.Builder().addInterceptor(loggingInterceptor)
        // Se añaden las cabeceras
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Authorization", "Basic MDAwMTIzMDAwQUJD")
            chain.proceed(request.build())
        }
        .build()

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/api/payments/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideQuoteApliClient(retrofit: Retrofit): TransactionClient {
        return retrofit.create(TransactionClient::class.java)
    }


}

