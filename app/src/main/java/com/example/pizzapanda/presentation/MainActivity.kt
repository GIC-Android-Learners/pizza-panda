package com.example.pizzapanda.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pizzapanda.presentation.admin.adminComponents.AdminScreen
import com.example.pizzapanda.presentation.example.ExampleViewModel
import com.example.pizzapanda.presentation.example.components.ExampleScreen
import com.example.pizzapanda.presentation.main.mainComponents.MainScreen
import com.example.pizzapanda.presentation.util.Screen
import com.example.pizzapanda.ui.theme.PizzaPandaTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var viewModel: ExampleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var pizzaJuiceFlag = mutableStateOf("pizza")

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
                            AdminScreen(viewModel = viewModel) {
                                navController.navigate(Screen.MainScreen.route)
                            }
                        }
                        composable(route = Screen.HomeScreen.route) {
                            Greeting("Pizza Panda") {
                                navController.navigate(Screen.ExampleScreen.route)
                            }
                        }
                        composable(route = Screen.ExampleScreen.route) {
                            ExampleScreen(viewModel = viewModel) {
                                navController.navigate(Screen.HomeScreen.route)
                            }
                        }
                        composable(route = Screen.PizzaUserScreen.route) {
                            pizzaJuiceFlag.value = "pizza"
                            Column() {
                                Row() {
                                    Column() {
                                        MainScreen(goToAdmin = {
                                            navController.navigate(Screen.AdminScreen.route)
                                        })
                                    }
                                }
                            }
                        }
                        composable(route = Screen.JuiceUserScreen.route) {
                            pizzaJuiceFlag.value = "juice"
                            Column() {
                                Row() {
                                    Column() {
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

@Composable
fun Greeting(name: String, goToExample: () -> Unit) {
    Column {
        Text(text = "Hello $name!")
        Button(onClick = {
            goToExample()
        }) {
            Text(text = "Show Examples")
        }
    }
}
