package com.app.spendr.data

import android.app.Application
import android.util.Log
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.app.spendr.presentation.widget.MyAppWidget
import com.app.spendr.presentation.widget.WidgetWorker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DataViewModel(application:Application): AndroidViewModel(application) {
    val readAllData : LiveData<List<Transaction>>
    private val repository: TransactionRepository

    init {
        val transactionDao = TransactionDatabase.getDatabase(application).transactionDao()
        repository = TransactionRepository(transactionDao)
        readAllData = repository.readAllData
    }

    fun addTransaction(transaction: Transaction){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTransaction(transaction)
            WidgetWorker.enqueue(
                context = getApplication<Application>().applicationContext,
                instantUpdate = true)
        }
    }
    fun deleteTransaction(transaction: Transaction){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTransaction(transaction)
            WidgetWorker.enqueue(
                context = getApplication<Application>().applicationContext,
                instantUpdate = true)        }
    }
    fun deleteAllTransaction(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAllTransaction()
        }
    }

}