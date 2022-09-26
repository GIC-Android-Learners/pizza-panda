package com.example.pizzapanda.presentation.example.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.pizzapanda.presentation.example.ExampleEvent
import com.example.pizzapanda.presentation.example.ExampleViewModel

@Composable
fun ExampleScreen(viewModel: ExampleViewModel, backToHome: () -> Unit) {
    Column {
        Button(onClick = {
            backToHome()
        }) {
            Text(text = "Back to Home")
        }
        ExampleCreator(
            create = {
                viewModel.onEvent(ExampleEvent.Add(name = it))
            }
        )

        ExampleList(examples = viewModel.exampleState.value.exampleList)
    }
}