package com.app.spendr.presentation.stats

import com.app.spendr.data.Transaction


fun extractData(transactionData: List<Transaction>):Stats {
    var balance: Int = 0
    var toAdd = 0
    var toSubtract = 0
    transactionData.forEach {
        if (it.isSavings) {
            toAdd += it.amount
        } else {
            toSubtract += it.amount
        }
        balance = toAdd - toSubtract

    }


    return Stats(
        totalSpend = toSubtract,
        totalEarned = toAdd,
        balance = balance,
        spendLog = transactionData.filter { !it.isSavings }.sortedByDescending { it.amount },
        earnedLog = transactionData.filter { it.isSavings }.sortedByDescending { it.amount }
        )

}