package com.example.todoappjetpackcompose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {

    val tf = remember { mutableStateOf("") }
    val itemsList = remember { mutableStateListOf("Learn Kotlin", "Learn Compose") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Todo App") },
                colors = TopAppBarColors(
                    containerColor = Color.Blue,
                    actionIconContentColor = Color.White,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White,
                    scrolledContainerColor = Color.Blue,
                ),
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .weight(7F)
                    .padding(5.dp)

                ) {
                    TextField(value = tf.value, onValueChange = {
                        tf.value = it
                    })
                    Spacer(modifier = Modifier.width(5.dp))
                    Button(
                        modifier = Modifier
                            .weight(3F)
                            .height(60.dp),
                        onClick = { /*TODO*/ }) {
                        Text(text = "Add")
                    }
                }

                Spacer(modifier = Modifier.height(5.dp))
                LazyColumn {
                    items(
                        count = itemsList.size,
                        itemContent = {index ->
                            Text(text = itemsList[index])
                        }
                    )
                }
            }

        }
    )
}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen()
}