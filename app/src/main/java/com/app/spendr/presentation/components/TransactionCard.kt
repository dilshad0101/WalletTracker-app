package com.app.spendr.presentation.components

import android.content.Context
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.app.spendr.R
import com.app.spendr.presentation.editor.Description
import com.app.spendr.presentation.home.UsersCurrency
import kotlinx.coroutines.Dispatchers
import okhttp3.Dispatcher

@Composable
fun TransactionCard(
    title: String,
    description: String,
    amount: Int,
    isSavings : Boolean,
    onDelete: () -> Unit,
    savedCurrency: UsersCurrency
){
    val context = LocalContext.current

    val sign = if (isSavings) "+" else "-"
    Column {
        var deleteMode by remember{ mutableStateOf(false) }

        AnimatedContent(targetState = deleteMode, label = "") {
            if (!it){
                Row(horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20))
                        .pointerInput(Unit) {
                            detectTapGestures(
                                onLongPress = {
                                    deleteMode = true
                                },
                                onTap = { }
                            )
                        }
                ){
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ){
                        Box(
                            modifier = Modifier
                                .width(60.dp)
                                .height(60.dp)
                                .clip(RoundedCornerShape(20))
                                .background(color = MaterialTheme.colorScheme.tertiaryContainer)
                                .wrapContentSize(Alignment.Center),

                            ) {
                            //Size 80
                            val imageSize = 68

                            IconsFromURL(
                                context = context,
                                url = when(description){
                                    Description.Family.text -> "https://img.icons8.com/metro/$imageSize/000000/family.png"
                                    Description.Food.text -> "https://img.icons8.com/ios-filled/$imageSize/000000/hamburger.png"
                                    Description.Friend.text -> "https://img.icons8.com/ios-filled/$imageSize/000000/hang-10.png"
                                    Description.Online.text -> "https://img.icons8.com/ios-filled/$imageSize/000000/online-payment-.png"
                                    Description.Other.text -> "https://img.icons8.com/ios-glyphs/$imageSize/000000/cost.png"
                                    Description.Rent.text -> "https://img.icons8.com/ios-glyphs/$imageSize/000000/sell-property--v1.png"
                                    Description.Reward.text -> "https://img.icons8.com/ios-filled/$imageSize/000000/gift--v1.png"
                                    Description.Salary.text -> "https://img.icons8.com/ios-glyphs/$imageSize/000000/coin-in-hand.png"
                                    Description.Shopping.text -> "https://img.icons8.com/ios-filled/$imageSize/000000/shopping-bag.png"
                                    else -> {
                                        "https://img.icons8.com/ios-filled/80/000000/merchant-account.png"
                                    }
                                }
                            )
                        }
                        Column {
                            Text(text = title,
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Text(text = description,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                    Text(
                        text = sign+ savedCurrency.symbols +amount,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(end = 6.dp)
                    )

                }
            }else{
                Card(
                    elevation = CardDefaults.cardElevation(0.dp),
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(20))
                        .clickable {
                            onDelete.invoke()
                            deleteMode = false
                        }
                ){
                    Row(verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth().wrapContentWidth(Alignment.CenterHorizontally)
                        ) {
                        Icon(imageVector = Icons.Filled.Delete,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier
                                .size(40.dp)

                        )
                        IconButton(
                            onClick = { deleteMode = false },
                            interactionSource = remember{ MutableInteractionSource() },
                            modifier = Modifier.padding(start = 40.dp)
                        ) {
                            Icon(Icons.Default.Close,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary
                                )

                        }

                    }

                }
            }
        }

    }


}

@Composable
fun IconsFromURL(
    url: String,
    modifier: Modifier = Modifier,
    context: Context) {

    val imageRequest = ImageRequest.Builder(context)
        .data(url)
        .dispatcher(Dispatchers.IO)
        .memoryCacheKey(url)
        .diskCacheKey(url)
        .diskCachePolicy(CachePolicy.ENABLED)
        .memoryCachePolicy(CachePolicy.ENABLED)
        .build()
    SubcomposeAsyncImage(model =imageRequest,
        contentDescription =null,
        contentScale = ContentScale.FillBounds,
        error = {
            Icon(painter = painterResource(id = R.drawable.baseline_person_24),null)
        },
        loading = {
            CircularProgressIndicator()
        },
        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
        modifier = modifier
    )
}