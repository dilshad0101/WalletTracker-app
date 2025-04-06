package com.app.spendr.presentation.stats

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.app.spendr.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardForTips(
    recommendation : Pair<String,String>,
){
    Card(
        onClick = {  },
        elevation = CardDefaults.cardElevation(Dp.Hairline),
        shape = RoundedCornerShape(20),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
            contentColor = MaterialTheme.colorScheme.primary
            )
    ) {

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 10.dp)
                .fillMaxWidth()
        ) {
            Column(modifier = Modifier
                .padding(end = 20.dp)
                .weight(4/5f)) {
                Text(text = recommendation.first,
                    style = MaterialTheme.typography.displaySmall,
                    color = MaterialTheme.colorScheme.primary)

                Text(text = recommendation.second,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary)
            }

            IconButton(
                onClick = {},
                modifier = Modifier){
                Icon(
                    Icons.Default.KeyboardArrowRight,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                    )
            }

        }

    }
}
