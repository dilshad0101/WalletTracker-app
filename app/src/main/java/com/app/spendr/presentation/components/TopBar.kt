package com.app.spendr.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.app.spendr.R
import com.app.spendr.presentation.components.TabBar

@Composable
fun TopBar(
    onFirstBtnClick:() -> Unit,
    firstBtnIcon: Int,
    onSecondBtnClick: () -> Unit,
    secondBtnIcon: Int,
    showNavTab: Boolean = false,
    onStatisticsSection: ((Boolean) -> Unit)? = null

){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {
        FilledIconButton(onClick = onFirstBtnClick,
            shape = RoundedCornerShape(100),
            colors = IconButtonDefaults
                .filledIconButtonColors(
                    containerColor = MaterialTheme.colorScheme.inversePrimary,
                    contentColor = MaterialTheme.colorScheme.primary
                )
        ) {
            Icon(
                painter = painterResource(firstBtnIcon),
                contentDescription = null)
        }

        if(showNavTab && onStatisticsSection != null){
            TabBar(FirstTabImg = R.drawable.icons8_home,
                FirstTabText = "Home",
                SecondTabImg = R.drawable.ic_action_name,
                SecondTabText = "Statistics",
                onStatisticsSection = {
                    onStatisticsSection(it)
                }
                )
        }
        
        FilledIconButton(onClick = onSecondBtnClick,
            shape = RoundedCornerShape(100),
            colors = IconButtonDefaults
                .filledIconButtonColors(
                    containerColor = MaterialTheme.colorScheme.inversePrimary,
                    contentColor = MaterialTheme.colorScheme.primary
                )
        ) {
            Icon(
                painter = painterResource(secondBtnIcon),
                contentDescription = null)
        }
    }
}
