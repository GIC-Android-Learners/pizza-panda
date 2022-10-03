package com.example.pizzapanda.presentation.util

sealed class Screen(val route: String) {
    object MainScreen : Screen("main")
    object AdminScreen : Screen("admin")
    object PizzaUserScreen : Screen("pizza")
    object JuiceUserScreen : Screen("juice")
}
