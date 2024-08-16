package com.techne.trackerapp.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ListScreen(
    modifier: Modifier = Modifier, navController: NavController,
    viewModel: UserViewModel,
) {
    val users = viewModel.state.users

        Column(modifier = Modifier.padding(10.dp,50.dp)) {
            Text(
                text = "#52Week Saving Challenge",
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp)
            )
            Spacer(modifier = Modifier.height(10.dp))

            if (users.isNullOrEmpty()) {
                Text(text = "No current data", style = TextStyle(fontWeight = FontWeight.Bold))

            } else {
                LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
                    items(items = users) { data ->
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                        ) {
                            ListItem(key = "Week:", value = data.week.toString())
                            ListItem(key = "Date:", value = data.date)
                            ListItem(key = "Amount:", value = data.amount.toString())
                            ListItem(key = "Total Amount:", value = data.totalAmount.toString())
                        }
                    }
                }

            }
        }

}

@Composable
fun ListItem(key: String, value: String) {
          Row(horizontalArrangement = Arrangement.SpaceAround) {
              Text(text = key)
              Spacer(modifier = Modifier.height(20.dp))
              Text(text = value)
          }

}




