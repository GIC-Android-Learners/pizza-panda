package com.example.pizzapanda.presentation.admin.components

import androidx.compose.runtime.Composable
import com.example.pizzapanda.presentation.main.components.SurfaceControl

@Composable
fun MainScreen(goToAdmin: () -> Unit) {
    SurfaceControl(goToAdmin = { goToAdmin() })
}