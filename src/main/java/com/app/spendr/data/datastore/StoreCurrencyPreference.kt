package com.app.spendr.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreCurrencyPreference(private val context: Context) {
    // used to store Currency preference of user (eg: USD, GBP, INR)
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("USER_PREF")
        val USER_CURRENCY_KEY = stringPreferencesKey("user_currency")
    }

    val getCurrencyPreference: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[USER_CURRENCY_KEY] ?: ""
        }

    suspend fun saveCurrencyPreference(name: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_CURRENCY_KEY] = name
        }
    }
}