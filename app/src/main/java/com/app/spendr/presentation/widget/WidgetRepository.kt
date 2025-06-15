package com.app.spendr.presentation.widget

import android.content.Context
import com.app.spendr.data.TransactionDatabase
import com.app.spendr.data.datastore.StoreCurrencyPreference
import com.app.spendr.util.streamlinePreference
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.lastOrNull
import kotlinx.serialization.Serializable
import java.time.LocalDate
import java.time.YearMonth


class WidgetDataRepository(context: Context) {
    private val dao = TransactionDatabase.getDatabase(context).transactionDao()
    private val currencyPreference = StoreCurrencyPreference(context)

    suspend fun getWidgetSpendData(): SpendData {
        val allTransactions = dao.getAllTransactionsForWidget()
        val today = LocalDate.now()
        val currentMonth = YearMonth.now()

        val todaySpend = allTransactions
            .filter { it.date == today && !it.isSavings }
            .sumOf { it.amount }

        val monthlySpend = allTransactions
            .filter { YearMonth.from(it.date) == currentMonth && !it.isSavings }
            .sumOf { it.amount }

        val savedCurrency = streamlinePreference(currencyPreference.getCurrencyPreference.firstOrNull())


        return SpendData.Available(
            monthlySpend = monthlySpend,
            todaySpend = todaySpend,
            currency = savedCurrency.symbols
        )
    }
}

@Serializable
sealed interface SpendData{

    @Serializable
    data object Loading: SpendData

    @Serializable
    data class Available(
        val monthlySpend: Int,
        val todaySpend: Int,
        val currency: String?
    ): SpendData

    @Serializable
    data class Unavailable(val msg:String): SpendData

}


