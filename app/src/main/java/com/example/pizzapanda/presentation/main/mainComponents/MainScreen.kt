package com.example.pizzapanda.presentation.main.mainComponents

import androidx.compose.runtime.Composable
import com.example.pizzapanda.presentation.example.ExampleViewModel

@Composable
fun MainScreen(viewModel: ExampleViewModel, goToAdmin: () -> Unit) {
    SurfaceControl { goToAdmin() }
}