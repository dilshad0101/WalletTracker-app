package com.app.spendr.data

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTransaction(transaction: Transaction)

    @Query("SELECT * FROM app_data ORDER By id ASC")
    fun readAllData(): LiveData<List<Transaction>>


    //For Widget
    @Query("SELECT * FROM app_data ORDER BY id ASC")
    suspend fun getAllTransactionsForWidget(): List<Transaction>


    @Delete
    suspend fun deleteTransaction(transaction: Transaction)

    @Query("DELETE FROM app_data")
    suspend fun deleteAllTransaction()
}