package com.app.spendr.presentation.stats

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.app.spendr.data.Transaction
import com.app.spendr.presentation.home.TransactionCard

@Composable
fun TabSection(stats: Stats,
               onGraphChange: (GraphType)-> Unit,
               deleteTransaction: (Transaction) -> Unit
               ){

    val tabs = listOf(
        Pair(0,"Income"),
        Pair(1,"Expense")
    )
    var selectedTabIndex by remember {
        mutableStateOf(tabs[0].first)
    }
    TabRow(
        selectedTabIndex = selectedTabIndex,
        divider = {},
        indicator = {},
        modifier = Modifier
            .clip(RoundedCornerShape(40))
            .background(MaterialTheme.colorScheme.tertiaryContainer)
            .height(40.dp)
    ) {
        tabs.forEach {
            Tab(
                selected = selectedTabIndex == it.first,
                onClick = {
                    selectedTabIndex = it.first
                    onGraphChange.invoke(
                        if (it.first == 0) GraphType.EARNED else GraphType.SPENT) },
                text = {
                    Text(
                        text = it.second,
                        style = MaterialTheme.typography.displaySmall,
                        color = MaterialTheme.colorScheme.primary
                )},
                modifier = Modifier
                    .clip(RoundedCornerShape(40))
                    .background(
                        if (selectedTabIndex == it.first) MaterialTheme.colorScheme.scrim else MaterialTheme.colorScheme.tertiaryContainer
                    )
                )
        }
    }
    TabSections(selectedTabIndex,
        stats,
        deleteTransaction = { deleteTransaction.invoke(it)}
        )
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun TabSections(selectedTabIndex: Int,
                stats: Stats,
                deleteTransaction: (Transaction) -> Unit){
    AnimatedContent(targetState = selectedTabIndex) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.background(MaterialTheme.colorScheme.background)
                .padding(top = 20.dp)
        ){
            (if(selectedTabIndex == 0) stats.earnedLog.sortedByDescending { it.amount } else stats.spendLog.sortedByDescending { it.amount } ).forEach{
                TransactionCard(
                    title = it.title,
                    description = it.description,
                    amount = it.amount,
                    isSavings = it.isSavings,
                    onDelete = {deleteTransaction.invoke(it)}
                    )
            }
        }
    }
}