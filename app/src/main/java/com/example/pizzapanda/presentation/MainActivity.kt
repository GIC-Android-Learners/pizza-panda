package com.example.pizzapanda.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pizzapanda.presentation.admin.components.AdminScreen
import com.example.pizzapanda.presentation.admin.components.MainScreen
import com.example.pizzapanda.presentation.util.Screen
import com.example.pizzapanda.ui.theme.PizzaPandaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val pizzaJuiceFlag = mutableStateOf("pizza")

        setContent {
            PizzaPandaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.MainScreen.route
                    ) {
                        // MSYM Main Screen
                        composable(route = Screen.MainScreen.route) {
                            MainScreen(goToAdmin = {
                                navController.navigate(Screen.AdminScreen.route)
                            })
                        }
                        // MSYM Admin Screen
                        composable(route = Screen.AdminScreen.route) {
                            AdminScreen {
                                navController.navigate(Screen.MainScreen.route)
                            }
                        }
                        // MSYM
                        composable(route = Screen.AdminScreen.route) {
                            AdminScreen {
                                navController.navigate(Screen.MainScreen.route)
                            }
                        }
                        composable(route = Screen.PizzaUserScreen.route) {
                            pizzaJuiceFlag.value = "pizza"
                            Column {
                                Row {
                                    Column {
                                        MainScreen(goToAdmin = {
                                            navController.navigate(Screen.AdminScreen.route)
                                        })
                                    }
                                }
                            }
                        }
                        composable(route = Screen.JuiceUserScreen.route) {
                            pizzaJuiceFlag.value = "juice"
                            Column {
                                Row {
                                    Column {
                                        MainScreen(goToAdmin = {
                                            navController.navigate(Screen.AdminScreen.route)
                                        })
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}