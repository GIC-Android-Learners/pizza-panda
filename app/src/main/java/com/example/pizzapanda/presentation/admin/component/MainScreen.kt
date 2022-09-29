package com.example.pizzapanda.presentation.main.mainComponents

import androidx.compose.runtime.Composable
import com.example.pizzapanda.presentation.example.ExampleViewModel

@Composable
fun MainScreen(goToAdmin: () -> Unit) {
    SurfaceControl(goToAdmin = { goToAdmin() })
}