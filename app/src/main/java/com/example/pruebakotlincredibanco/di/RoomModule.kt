package com.example.pruebakotlincredibanco.di

import android.content.Context
import androidx.room.Room
import com.example.pruebakotlincredibanco.data.database.TransactionDatabase

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {


    private  val TRANSACTION_DATABASE_NAME = "transaction_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, TransactionDatabase::class.java, TRANSACTION_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun providesQuoteDao(db:TransactionDatabase) = db.getTransactionDao()
}