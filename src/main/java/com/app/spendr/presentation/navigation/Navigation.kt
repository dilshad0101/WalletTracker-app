package com.app.spendr.presentation.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.spendr.R
import com.app.spendr.data.DataViewModel
import com.app.spendr.data.Transaction
import com.app.spendr.data.datastore.StoreCurrencyPreference
import com.app.spendr.presentation.TopBar
import com.app.spendr.presentation.editor.ExpenseScreen
import com.app.spendr.presentation.home.HomeScreen
import com.app.spendr.presentation.editor.SavingsScreen
import com.app.spendr.presentation.stats.StatisticsScreen
import com.app.spendr.presentation.stats.extractData


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Navigation(owner: ViewModelStoreOwner){

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val dataStore = StoreCurrencyPreference(context)
    val savedCurrency = dataStore.getCurrencyPreference.collectAsState(initial = "")


    val dataViewModel = ViewModelProvider(owner)[DataViewModel::class.java]
    val _transactionData: LiveData<List<Transaction>> = dataViewModel.readAllData
    val transactionData: List<Transaction> by _transactionData.observeAsState(initial = emptyList())
    val stats = extractData(transactionData)

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route){
        composable(Screen.MainScreen.route){
            var isInStatisticsSection by remember {
                mutableStateOf(false)
            }
            Column(modifier = Modifier
                .padding(horizontal = 20.dp)) {
                TopBar(
                    firstBtnIcon = R.drawable.baseline_dehaze_24,
                    onFirstBtnClick = {},
                    secondBtnIcon = R.drawable.outline_notifications_24,
                    onSecondBtnClick = {},
                    showNavTab = true,
                    onStatisticsSection = {
                        isInStatisticsSection = (it)
                    }

                )
                AnimatedContent(
                    isInStatisticsSection,
                    transitionSpec = {ContentTransform(
                        targetContentEnter = slideInHorizontally(tween(300)),
                        initialContentExit = slideOutOfContainer(towards = AnimatedContentScope.SlideDirection.Left)
                    )}
                ) { targetState:Boolean->
                    when(targetState){
                        true ->StatisticsScreen(
                            extractData(transactionData),
                            deleteTransaction = {
                                dataViewModel.deleteTransaction(it)
                            }
                        )
                        false ->HomeScreen(
                            navController,
                            transactionData,
                            deleteTransaction = {dataViewModel.deleteTransaction(it)}
                            )
                    }
                }


            }

        }

        composable(Screen.Savings.route){
            SavingsScreen(
                navController,
            onSave = {
                dataViewModel.addTransaction(it)
                navController.navigate(Screen.MainScreen.route)

            }
            )
        }
        composable(Screen.Expense.route){
            ExpenseScreen(navController = navController, onSave = {
                dataViewModel.addTransaction(it)
                navController.navigate(Screen.MainScreen.route)
            })
        }

    }
}

