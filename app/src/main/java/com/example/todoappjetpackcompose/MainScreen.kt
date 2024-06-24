package com.example.todoappjetpackcompose

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {

    val myContext = LocalContext.current
    val tf = remember { mutableStateOf("") }
    val itemsList = readData(myContext)

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

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)

                ) {
                    TextField(value = tf.value, onValueChange = {
                        tf.value = it
                    })
                    Spacer(modifier = Modifier.width(5.dp))
                    Button(
                        modifier = Modifier
                            .weight(2F)
                            .height(60.dp),
                        onClick = {
                            if (tf.value.isNotEmpty()) {
                                itemsList.add(tf.value)
                                tf.value = ""
                                writeToFile(itemsList, myContext)
                            } else {
                                Toast.makeText(myContext, "Please enter something", Toast.LENGTH_SHORT).show()
                            }
                        }) {
                        Text(text = "Add")
                    }
                }

                Spacer(modifier = Modifier.height(5.dp))
                LazyColumn {
                    items(
                        count = itemsList.size,
                        itemContent = { index ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(60.dp)
                                    .padding(bottom = 5.dp, start = 5.dp, end = 5.dp),
                                shape = RoundedCornerShape(0.dp),

                                onClick = { /*TODO*/ }) {
                                Row(
                                    modifier = Modifier.padding(5.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        modifier = Modifier.weight(8F),
                                        text = itemsList[index]
                                    )
                                    Row {

                                        IconButton(
                                            onClick = { /*TODO*/ }) {
                                            Icon(
                                                imageVector = Icons.Default.Delete,
                                                contentDescription = "Delete",
                                                tint = Color.Red
                                            )
                                        }
                                        IconButton(onClick = { /*TODO*/ }) {
                                            Icon(
                                                imageVector = Icons.Default.Edit,
                                                contentDescription = "Edit",
                                                tint = Color.Blue
                                            )
                                        }
                                    }
                                }
                            }
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