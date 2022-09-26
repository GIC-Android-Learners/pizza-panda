package com.example.pizzapanda.presentation.example.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pizzapanda.domain.model.Example

@Composable
fun ExampleList(examples: List<Example>) {
    Column(modifier = Modifier.padding(10.dp)) {
        for (example in examples) {
            ExampleItem(example = example)
        }
    }
}