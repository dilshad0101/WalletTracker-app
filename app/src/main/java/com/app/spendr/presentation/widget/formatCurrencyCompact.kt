package com.app.spendr.presentation.widget

import android.content.Context
import android.icu.text.CompactDecimalFormat
import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.asLiveData
import com.app.spendr.data.datastore.StoreCurrencyPreference
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

fun formatCurrencyCompact(amount: Int): String {
    val locale = Locale.getDefault()
    val formatter = CompactDecimalFormat.getInstance(locale, CompactDecimalFormat.CompactStyle.SHORT)
    val formatted = formatter.format(amount).replace(".0", "")
    return formatted
}
