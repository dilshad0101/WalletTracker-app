package com.app.spendr.presentation.navigation

import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.spendr.data.DataViewModel
import com.app.spendr.data.Transaction
import com.app.spendr.data.datastore.StoreCurrencyPreference
import com.app.spendr.presentation.editor.ExpenseScreen
import com.app.spendr.presentation.editor.SavingsScreen
import com.app.spendr.util.streamlinePreference
import kotlinx.coroutines.launch

@Composable
fun Navigation(owner: ViewModelStoreOwner){

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val dataStore = StoreCurrencyPreference(context)
    val savedCurrency by dataStore.getCurrencyPreference.collectAsState(initial = "")

    val dataViewModel = ViewModelProvider(owner)[DataViewModel::class.java]
    val _transactionData: LiveData<List<Transaction>> = dataViewModel.readAllData
    val transactionData: List<Transaction> by _transactionData.observeAsState(initial = emptyList())

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route){
        composable(Screen.MainScreen.route){
            EntryScreenRoute(
                deletionEvent = {dataViewModel.deleteTransaction(it)},
                transactionData = transactionData,
                changePreferenceEvent = {
                    scope.launch {
                        dataStore.saveCurrencyPreference(it) }
                },
                savedCurrency = savedCurrency,
                navController = navController
            )
        }

        composable(Screen.Savings.route){
            SavingsScreen(
                navController,
            onSave = {
                dataViewModel.addTransaction(it)
                navController.navigate(Screen.MainScreen.route)
            },
                savedCurrency = streamlinePreference(savedCurrency)
            )
        }
        composable(Screen.Expense.route){
            ExpenseScreen(navController = navController, onSave = {
                dataViewModel.addTransaction(it)
                navController.navigate(Screen.MainScreen.route)
            },
            savedCurrency = streamlinePreference(savedCurrency)
            )
        }

    }
}

