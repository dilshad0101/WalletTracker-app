package com.app.spendr.presentation.components

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.unit.dp
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource


@Composable
fun TabBar(
    FirstTabImg: Int ,
    FirstTabText: String,
    SecondTabImg: Int,
    SecondTabText: String,
    onStatisticsSection: (Boolean) -> Unit

){
    val height = 40.dp
    val widthWhenDisabled = 60.dp
    val widthWhenEnabled = 100.dp


    var selectedTabIndex by remember { mutableStateOf(0) }

    val tabs = listOf(
        TabItem(painterResource(id = FirstTabImg), FirstTabText),
        TabItem(painterResource(id = SecondTabImg), SecondTabText))

    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .width(widthWhenDisabled + widthWhenEnabled)
            .background(MaterialTheme.colorScheme.tertiaryContainer)
    ) {
        Tab(selected = selectedTabIndex == 0,
            selectedContentColor = MaterialTheme.colorScheme.primary,
            onClick = {
                selectedTabIndex = 0;
                onStatisticsSection.invoke(false)
                      },
            modifier =
            if (selectedTabIndex == 0){
                Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .height(height)
                    .width(widthWhenEnabled)
                    .background(MaterialTheme.colorScheme.scrim)
            }else{
                Modifier
                    .clip(
                        RoundedCornerShape(
                            topStart = 20.dp,
                            bottomStart = 20.dp
                        )
                    )
                    .background(MaterialTheme.colorScheme.tertiaryContainer)
                    .height(height)
                    .width(widthWhenDisabled)
            }
        ) {
            if (selectedTabIndex == 0){
                Text(text = tabs[0].text)
            }else{
                Icon(tabs[0].icon, contentDescription = null )
            }
        }

        Tab(selected = selectedTabIndex == 1,
            selectedContentColor = MaterialTheme.colorScheme.primary,
            onClick = {
                selectedTabIndex = 1
                onStatisticsSection.invoke(true)
                      },
            modifier =
            if (selectedTabIndex == 1){
                Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .width(widthWhenEnabled)
                    .height(height)
                    .background(MaterialTheme.colorScheme.scrim)
            }else{
                Modifier
                    .clip(
                        RoundedCornerShape(
                            topEnd = 20.dp,
                            bottomEnd = 20.dp
                        )
                    )
                    .height(height)
                    .width(widthWhenDisabled)
                    .background(MaterialTheme.colorScheme.tertiaryContainer)
            }
        ){
            if (selectedTabIndex == 1){
                Text(text = tabs[1].text)
            }else{
                Icon(tabs[1].icon, contentDescription = null )
            }
        }
    }
}

data class TabItem(val icon: Painter, val text: String)