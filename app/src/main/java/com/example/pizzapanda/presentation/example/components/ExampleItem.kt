package com.example.pizzapanda.presentation.example.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.pizzapanda.domain.model.Example

@Composable
fun ExampleItem(example: Example) {
    Text("Example: ${example.name}")
}