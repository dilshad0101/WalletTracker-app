package com.app.spendr.presentation.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.app.spendr.R
import com.app.spendr.data.Transaction
import com.app.spendr.presentation.TopBar
import com.app.spendr.presentation.components.NotificationWindow
import com.app.spendr.presentation.home.HomeScreen
import com.app.spendr.presentation.stats.StatisticsScreen
import com.app.spendr.presentation.stats.extractData
import com.app.spendr.util.streamlinePreference

@OptIn(ExperimentalAnimationApi::class)
@Composable

fun EntryScreenRoute(
    deletionEvent: (Transaction) -> Unit,
    transactionData: List<Transaction>,
    changePreferenceEvent: (String) -> Unit,
    savedCurrency : String?,
    navController : NavController
){
    var isInStatisticsSection by remember {
        mutableStateOf(false)
    }
    var showNotificationWindow by remember{ mutableStateOf(false) }

    Column(modifier = Modifier
        .padding(horizontal = 20.dp)) {
        TopBar(
            firstBtnIcon = R.drawable.baseline_dehaze_24,
            onFirstBtnClick = {},
            secondBtnIcon = R.drawable.outline_notifications_24,
            onSecondBtnClick = { showNotificationWindow = true },
            showNavTab = true,
            onStatisticsSection = {
                isInStatisticsSection = (it)
            })

        if(showNotificationWindow) NotificationWindow { showNotificationWindow = false }

        AnimatedContent(
            isInStatisticsSection,
            transitionSpec = {
                ContentTransform(
                targetContentEnter = slideInHorizontally(tween(300)),
                initialContentExit = slideOutOfContainer(towards = AnimatedContentTransitionScope.SlideDirection.Left)
            ) }, label = ""
        ) { targetState:Boolean->
            when(targetState){
                true -> StatisticsScreen(
                    extractData(transactionData),
                    deleteTransaction = { deletionEvent(it) },
                    savedCurrency = streamlinePreference(savedCurrency))

                false -> HomeScreen(
                    navController,
                    transactionData,
                    deleteTransaction = {
                        deletionEvent.invoke(it)
                    },
                    changePreference = { changePreferenceEvent(it.text) },
                    savedCurrency = streamlinePreference(savedCurrency)
                )
            }
        }


    }
}
