package com.app.spendr.presentation.stats

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.app.spendr.data.Transaction
import kotlin.random.Random

@Composable
fun StatisticsScreen(
    stats: Stats,
    deleteTransaction : (Transaction) -> Unit
){
    var graphInDisplay by remember {
        mutableStateOf(GraphType.EARNED)
    }
    val listInDisplay = when(graphInDisplay){
        GraphType.EARNED -> stats.earnedLog.sortedByDescending { it.date }
            GraphType.SPENT -> stats.spendLog.sortedByDescending { it.date }
    }

    Surface(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)) {

        LazyColumn(modifier = Modifier) {
            item{
                Column(modifier = Modifier
                    .padding(vertical = 10.dp)
                    .clip(RoundedCornerShape(10))
                    .background(MaterialTheme.colorScheme.tertiaryContainer)

                ){

                    Column(modifier = Modifier.padding(top = 22.dp, start = 15.dp, end = 15.dp)){
                        Text(text = "Current Balance",
                            style = MaterialTheme.typography.displaySmall,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Row {
                            Text(text = "$${stats.balance}",
                                style = MaterialTheme.typography.displayLarge,
                                color = MaterialTheme.colorScheme.primary)
                        }
                        if(listInDisplay.size > 5){
                            LineChart(
                                data = listOf(
                                    Pair("${listInDisplay[0].date.dayOfMonth} ${listInDisplay[0].date.month.name.take(3)}", listInDisplay[0].amount.toDouble()),
                                    Pair("${listInDisplay[1].date.dayOfMonth} ${listInDisplay[1].date.month.name.take(3)}", listInDisplay[1].amount.toDouble()),
                                    Pair("${listInDisplay[2].date.dayOfMonth} ${listInDisplay[2].date.month.name.take(3)}", listInDisplay[2].amount.toDouble()),
                                    Pair("${listInDisplay[3].date.dayOfMonth} ${listInDisplay[3].date.month.name.take(3)}", listInDisplay[3].amount.toDouble()),
                                    Pair("${listInDisplay[4].date.dayOfMonth} ${listInDisplay[4].date.month.name.take(3)}", listInDisplay[4].amount.toDouble()),
                                    Pair("${listInDisplay[5].date.dayOfMonth} ${listInDisplay[5].date.month.name.take(3)}", listInDisplay[5].amount.toDouble()),

                                ),
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
                    deleteTransaction = {deleteTransaction.invoke(it)}
                )

            }

        }
    }

}

enum class GraphType{
    EARNED, SPENT
}