package com.example.pizzapanda.presentation.util

sealed class Screen(val route: String) {
    object HomeScreen: Screen("home")
    object ExampleScreen: Screen("example")
    object MainScreen: Screen("main")
    object AdminScreen: Screen("admin")
    object PizzaUserScreen : Screen("pizza")
    object JuiceUserScreen : Screen("juice")
}
