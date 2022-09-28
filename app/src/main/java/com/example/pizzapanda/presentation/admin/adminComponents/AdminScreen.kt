package com.example.pizzapanda.presentation.admin.adminComponents

import androidx.compose.runtime.Composable
import com.example.pizzapanda.presentation.example.ExampleViewModel

@Composable
fun AdminScreen(viewModel: ExampleViewModel, backToHome: () -> Unit) {
    AdminOverView(backToHome)
}