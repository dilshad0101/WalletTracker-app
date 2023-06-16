package com.app.spendr.presentation.home

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.app.spendr.R

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun BalanceCard(balance:()-> Int){

    var balanceState by remember{ mutableStateOf(0) }
    balanceState = balance.invoke()

    Card(
        onClick = {},
        shape = RoundedCornerShape(10),
        elevation = CardDefaults.cardElevation(0.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(bottom = 5.dp, top = 5.dp)

    ) {
        Box(
            modifier =
            Modifier.background(
                Brush.linearGradient(
                    0.0f to Color(0xFF2E2E2E),
                    1f to Color(0xA4505050)
                )
                )){
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(horizontal = 20.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .paddingFromBaseline(bottom = 20.dp)
                ) {
                    Card(
                        onClick = { },
                        shape = RoundedCornerShape(30),
                        elevation = CardDefaults.cardElevation(0.dp)
                    ) {
                        Row() {
                            Text(text = "USD")
                            //Flag
                        }

                    }
                    FilledIconButton(onClick = { },
                        shape = RoundedCornerShape(100),
                        colors = IconButtonDefaults
                            .filledIconButtonColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                contentColor = MaterialTheme.colorScheme.onPrimary
                            )
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.baseline_more_horiz_24),
                            contentDescription = null)
                    }

                }
                Column {
                    val transition = updateTransition(targetState = balanceState, label = "DigitTransition")

                    val digit = transition.animateInt(
                        label = "Digit",
                        transitionSpec = { tween(durationMillis = 1500, easing = LinearOutSlowInEasing) }
                    ) { state ->
                        state
                    }

                    Text("Your Balance", style = MaterialTheme.typography.displaySmall)
                    AnimatedContent(targetState = digit
                        ) {
                        Text(text = "$${it.value}.00", style = MaterialTheme.typography.displayMedium)

                    }
                }

            }
        }


    }
}