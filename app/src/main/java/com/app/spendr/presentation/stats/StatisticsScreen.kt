package com.app.spendr.presentation.stats

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.app.spendr.data.Transaction
import com.app.spendr.presentation.home.UsersCurrency
import kotlin.math.roundToInt
import kotlin.random.Random

@Composable
fun StatisticsScreen(
    stats: Stats,
    deleteTransaction : (Transaction) -> Unit,
    savedCurrency: UsersCurrency
){
    var graphInDisplay by remember {
        mutableStateOf(GraphType.EARNED)
    }
    val listInDisplay = when(graphInDisplay){
        GraphType.EARNED -> stats.earnedLog.sortedBy { it.date }
            GraphType.SPENT -> stats.spendLog.sortedBy { it.date }
    }

    Surface(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)) {
        LazyColumn(modifier = Modifier) {
            item{
                Column(modifier = Modifier
                    .padding(vertical = 10.dp)
                    .clip(RoundedCornerShape(5))
                    .background(MaterialTheme.colorScheme.tertiaryContainer)
                ){
                    Column(modifier = Modifier.padding(top = 22.dp, start = 15.dp, end = 15.dp)){
                        Row(
                            horizontalArrangement = Arrangement.Absolute.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column {
                                Text(text = "Current Balance",
                                    style = MaterialTheme.typography.displaySmall,
                                    color = MaterialTheme.colorScheme.primary
                                )
                                Row {
                                    Text(text = "${savedCurrency.symbols}${stats.balance}",
                                        style = MaterialTheme.typography.displayLarge,
                                        color = MaterialTheme.colorScheme.primary)
                                }
                            }
//                            TextButton(
//                                onClick = {}
//                            ){
//                                Text(text = "Expense",
//                                    style = MaterialTheme.typography.displaySmall)
//                            }
                        }
                        if (listInDisplay.size > 2) {
                            LineChart(
                                data = getSpacedTransactions(listInDisplay).take(listInDisplay.size+1).map { item ->
                                    val label = "${item.date.dayOfMonth} ${item.date.month.name.take(3)}"
                                    label to item.amount.toDouble()
                                },
                                modifier = Modifier
                                    .height(220.dp)
                                    .fillMaxWidth()
                                    .padding(top = 70.dp, bottom = 30.dp)
                            )
                        }else{
                            Box(contentAlignment = Alignment.CenterStart,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(top = 20.dp, bottom = 40.dp)
                            ){
                                Text(text = "Not enough data for a meaningful graph.\nPlease add more transactions.",
                                    style = MaterialTheme.typography.displaySmall,
                                    color = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .wrapContentHeight(Alignment.CenterVertically)
                                        .alpha(0.7f)
                                )


                            }
                        }
                    }

                }
            }

            item{
                Column(
                    verticalArrangement = Arrangement.spacedBy(14.dp),
                    modifier = Modifier.padding(bottom = 25.dp)
                ) {
                    Text(
                        text = "Recommendations",
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(top = 5.dp)
                    )


                    val recommendation = (stats.spendLog+stats.earnedLog).distinctBy { it.description }.sortedByDescending { it.amount }
                    if(recommendation.size >2){
                        CardForTips(getRecommendation(recommendation[0].description))
                        CardForTips(getRecommendation(recommendation[1].description))
                    }
                    else{
                        CardForTips(Pair("Insufficient Data for Personalized Advice","we require more transaction data to provide personalized advice. Please continue tracking your expenses in the app to accumulate sufficient data."))
                    }

                }
            }
            item {
                TabSection(
                    stats,
                    onGraphChange = {
                        graphInDisplay = it },
                    deleteTransaction = {deleteTransaction.invoke(it)},
                    savedCurrency
                )
            }
            item {
                Spacer(Modifier.height(80.dp))
            }

        }
    }

}

enum class GraphType{
    EARNED, SPENT
}


private fun getSpacedTransactions(fullList: List<Transaction>): List<Transaction> {
    val requiredPoints = 5

    return when {
        fullList.size <= requiredPoints -> fullList
        else -> {
            val step = (fullList.size - 1).toDouble() / (requiredPoints - 1)
            (0 until requiredPoints).map { i ->
                fullList[(i * step).roundToInt()]
            }
        }
    }
}
