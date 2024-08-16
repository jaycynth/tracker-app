package com.techne.trackerapp.presentation.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
fun UpdateScreen(
    modifier: Modifier = Modifier, navController: NavController,
    viewModel: UserViewModel,
) {
    val context = LocalContext.current
    var amount by remember { mutableStateOf("") }

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {

            OutlinedTextField(
                value = amount,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                onValueChange = { newText ->
                    amount = newText

                },
                label = { Text(text = "Enter amount") },
                placeholder = { Text(text = "Amount in Ksh") },
            )
            Spacer(modifier = Modifier.height(10.dp))

            Text(text = viewModel.amountError, color = Color.Red)

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {

                Button(onClick = {
                    if (viewModel.validateAmount(amount)) {
                        viewModel.onEvent(UserEvent.OnUpdate(amount.toDouble()))
                        amount = ""
                        Toast.makeText(context, "Updated", Toast.LENGTH_SHORT).show()
                    }

                }) {
                    Text("Update")
                }
                Spacer(modifier = Modifier.width(10.dp))
                Button(onClick = {
                    navController.navigate("trackerlist")

                }) {
                    Text("View list of weeks")
                }

            }

        }

    }

}