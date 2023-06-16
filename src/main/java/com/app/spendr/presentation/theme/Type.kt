package com.app.spendr.presentation.theme

import android.graphics.Typeface
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.app.spendr.R

val inter = FontFamily(
        Font(R.font.interblack, FontWeight.Black),
        Font(R.font.interbold, FontWeight.Bold),
        Font(R.font.interextrabold, FontWeight.ExtraBold),
        Font(R.font.interextralight, FontWeight.ExtraLight),
        Font(R.font.interlight,FontWeight.Light),
        Font(R.font.intermedium, FontWeight.Medium),
        Font(R.font.interregular, FontWeight.Normal),
        Font(R.font.intersemibold, FontWeight.SemiBold),
        Font(R.font.interthin,FontWeight.Thin)

)

// Set of Material typography styles to start with
val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = inter,
        fontWeight = FontWeight.Black,
        fontSize = 32.sp
    ),
    displayMedium = TextStyle(
        fontFamily = inter,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 28.sp
    ),
    displaySmall = TextStyle(
        fontFamily = inter,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = inter,
        fontWeight = FontWeight.Medium,
        fontSize = 22.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = inter,
        fontWeight = FontWeight.Medium,
        fontSize = 17.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = inter,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp
    ),
    bodySmall = TextStyle(
        fontFamily = inter,
        fontWeight = FontWeight.Light,
        fontSize = 12.sp
    ),
    labelMedium = TextStyle(
        fontFamily = inter,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp
    ),
    labelSmall = TextStyle(
        fontFamily = inter,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp
    ),


)