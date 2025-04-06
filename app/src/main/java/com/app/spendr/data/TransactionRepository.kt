package com.app.spendr.data

import androidx.lifecycle.LiveData

class TransactionRepository(private val transactionDao: TransactionDao){
    val readAllData: LiveData<List<Transaction>> = transactionDao.readAllData()

    suspend fun addTransaction(transaction: Transaction){
        transactionDao.addTransaction(transaction)
    }
    suspend fun deleteTransaction(transaction: Transaction){
        transactionDao.deleteTransaction(transaction)
    }

    suspend fun deleteAllTransaction(){
        transactionDao.deleteAllTransaction()
    }
}