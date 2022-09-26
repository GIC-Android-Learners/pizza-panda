package com.example.pizzapanda.presentation.example.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ExampleCreator(create: (String) -> Unit) {
    val name: MutableState<String> = remember {
        mutableStateOf("")
    }

    Column(modifier = Modifier.padding(10.dp)) {
        Row {
            Text("Name: ")
            TextField(value = name.value, onValueChange = {
                name.value = it
            })
        }

        Row {
            Button(onClick = {
                name.value = ""
            }) {
                Text("Clear")
            }
            Button(onClick = {
                create(name.value)
                name.value = ""
            }) {
                Text("Create")
            }
        }
    }
}