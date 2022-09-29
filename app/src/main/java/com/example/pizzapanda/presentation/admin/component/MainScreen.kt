package com.example.pizzapanda.presentation.main.mainComponents

import androidx.compose.runtime.Composable
import com.example.pizzapanda.presentation.example.ExampleViewModel

@Composable
fun MainScreen(
    viewModel: ExampleViewModel, goToAdmin: () -> Unit,
    goToPizzaList: () -> Unit,
    goToJuiceList: () -> Unit,
    pizzaJuiceFlag: String
) {
    SurfaceControl ( goToAdmin = {goToAdmin()},goToPizzaList = {goToPizzaList()},goToJuiceList = {goToJuiceList()},pizzaJuiceFlag )
}