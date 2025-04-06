package com.app.spendr.presentation.stats

import com.app.spendr.data.Transaction

data class Stats(
    val totalSpend: Int,
    val totalEarned: Int,
    val balance:Int,
    val spendLog: List<Transaction>,
    val earnedLog: List<Transaction>
)
