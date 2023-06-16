package com.app.spendr.presentation.editor

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.navigation.NavController
import com.app.spendr.R
import com.app.spendr.data.Transaction
import com.app.spendr.presentation.TopBar
import com.app.spendr.presentation.navigation.Screen
import kotlinx.coroutines.launch
import java.time.LocalDate

var _selectedChipValue = Description.Other.text
@Composable
fun ExpenseScreen(navController: NavController, onSave: (Transaction) -> Unit){
    Scaffold(containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.primary
    ) {
        BackHandler(true) {
            navController.navigate(Screen.MainScreen.route)
        }

        val date = LocalDate.now()

    var contentTextField by remember{
        mutableStateOf(TextFieldValue(""))
    }
    var amountTextField by remember{
        mutableStateOf(TextFieldValue("")) }
    var isChip1Enabled by remember {
        mutableStateOf(false)
    }
    var isChip2Enabled by remember {
        mutableStateOf(false)
    }
    var isChip3Enabled by remember {
        mutableStateOf(false)
    }
    var isChip4Enabled by remember {
        mutableStateOf(false)
    }
    var isChip5Enabled by remember {
        mutableStateOf(false)
    }
    var isChip6Enabled by remember {
        mutableStateOf(false)
    }
    var isChip7Enabled by remember {
        mutableStateOf(false)
    }

    var isChip1 by remember {
        mutableStateOf(true)
    }
    var isChip2 by remember {
        mutableStateOf(true)
    }
    var isChip3 by remember {
        mutableStateOf(true)
    }
    var isChip4 by remember {
        mutableStateOf(true)
    }
    var isChip5 by remember {
        mutableStateOf(true)
    }
    var isChip6 by remember {
        mutableStateOf(true)
    }
    var isChip7 by remember {
        mutableStateOf(true)
    }

    fun closeAll(doReverse: Boolean){
        isChip1 = doReverse
        isChip2 = doReverse
        isChip3 = doReverse
        isChip4 = doReverse
        isChip5 = doReverse
        isChip6 = doReverse
        isChip7 = doReverse
    }



    Column(
        modifier = Modifier.padding(horizontal = 20.dp)
    ){
        val context = LocalContext.current
        TopBar(
            onFirstBtnClick = { navController.navigate(Screen.MainScreen.route) },
            firstBtnIcon = R.drawable.baseline_arrow_back_24,
            onSecondBtnClick = {
                if(contentTextField.text.isNotBlank() && amountTextField.text.isDigitsOnly() && amountTextField.text.isNotBlank()){
                    onSave.invoke(
                        Transaction(
                            title = contentTextField.text,
                            description = _selectedChipValue,
                            amount = amountTextField.text.toInt(),
                            id = 0,
                            isSavings = false,
                            date = date
                        )
                    )
                }else{
                    Toast.makeText(context,"Invalid Input",Toast.LENGTH_SHORT).show()

                }

            },
            secondBtnIcon = R.drawable.baseline_save_24
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Add New\nExpense To Your\nRecord",
            minLines = 2,
            maxLines = 3,
            style = MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "To",
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(5.dp))
        TextField(
            value = contentTextField,
            onValueChange = {
                contentTextField = it },
            textStyle = MaterialTheme.typography.labelMedium,
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                focusedContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
                unfocusedContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
                focusedTextColor = MaterialTheme.colorScheme.primary,
                unfocusedTextColor = MaterialTheme.colorScheme.primary


            ),
            shape = RoundedCornerShape(20),
            modifier = Modifier
                .fillMaxWidth()

        )
        Column(modifier = Modifier.padding(vertical = 8.dp)) {

            Row(horizontalArrangement = Arrangement.Start){
                if(isChip1){
                    Chips(
                        text = Description.Friend.text,
                        isEnabled = isChip1Enabled,
                        onClick = {
                            isChip1Enabled = if (isChip1Enabled) {
                                closeAll(doReverse = true)
                                false

                            }else{
                                closeAll(doReverse = false)
                                isChip1 = true
                                true

                            }
                            _selectedChipValue = Description.Friend.text}
                    )
                }


                if(isChip2){
                    Chips(
                        text = Description.Family.text,
                        isEnabled = isChip2Enabled,
                        onClick = {
                            isChip2Enabled = if (isChip2Enabled) {
                                closeAll(doReverse = true)
                                false

                            }else{
                                closeAll(doReverse = false)
                                isChip2 = true
                                true

                            }
                            _selectedChipValue = Description.Family.text}
                    )
                }
                if(isChip3){
                    Chips(
                        text = Description.Food.text,
                        isEnabled = isChip3Enabled,
                        onClick = {
                            isChip3Enabled = if (isChip3Enabled) {
                                closeAll(doReverse = true)
                                false

                            }else{
                                closeAll(doReverse = false)
                                isChip3 = true
                                true

                            }
                            _selectedChipValue = Description.Food.text}
                    )
                }

                if(isChip4){
                    Chips(
                        text = Description.Rent.text,
                        isEnabled = isChip4Enabled,
                        onClick = {
                            isChip4Enabled = if (isChip4Enabled) {closeAll(doReverse = true)
                                false

                            }else{
                                closeAll(doReverse = false)
                                isChip4 = true
                                true

                            }
                            _selectedChipValue = Description.Rent.text}
                    )
                }

            }
            Row(horizontalArrangement = Arrangement.Start) {
                if(isChip5){
                    Chips(
                        text = Description.Online.text,
                        isEnabled = isChip5Enabled,
                        onClick = {
                            isChip5Enabled = if (isChip5Enabled) {closeAll(doReverse = true)
                                false

                            }else{closeAll(doReverse = false)
                                isChip5 = true
                                true

                            }
                            _selectedChipValue = Description.Online.text}
                    )
                }
                if(isChip6){
                    Chips(
                        text = Description.Shopping.text,
                        isEnabled = isChip6Enabled,
                        onClick = {
                            isChip6Enabled = if (isChip6Enabled) {closeAll(doReverse = true)
                                false

                            }else{
                                closeAll(doReverse = false)
                                isChip6 = true
                                true

                            }
                            _selectedChipValue = Description.Shopping.text}
                    )
                }

                if(isChip7){
                    Chips(
                        text = Description.Other.text,
                        isEnabled = isChip7Enabled,
                        onClick = {
                            isChip7Enabled = if (isChip7Enabled) {closeAll(doReverse = true)
                                false

                            }else{
                                closeAll(doReverse = false)
                                isChip7 = true
                                true

                            }
                            _selectedChipValue = Description.Other.text}
                    )
                }

            }

        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Amount",
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(5.dp))
        TextField(
            value = amountTextField,
            onValueChange = {
                amountTextField = it },
            textStyle = MaterialTheme.typography.labelMedium,
            prefix = {
                Text(
                    text = "$",
                    color = MaterialTheme.colorScheme.primary,)
            },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                focusedContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
                unfocusedContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
                focusedTextColor = MaterialTheme.colorScheme.primary
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            shape = RoundedCornerShape(20),
            modifier = Modifier
                .fillMaxWidth(2f)

        )

    }
}
}