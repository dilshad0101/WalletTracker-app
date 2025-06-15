package com.app.spendr.util

import com.app.spendr.presentation.home.*


//PreferenceDatastore cant store data of type UsersCurrency so it was converted to string of itsCurrency name.
// This function can be used to change the converted stored data back to UsersCurrency type

fun streamlinePreference(preferenceValue: String?): UsersCurrency {
    return when(preferenceValue) {
        GBP_VAL -> POUND
        EUR_VAL -> EURO
        INR_VAL -> INR
        null -> INR
        else -> USD
    }
}