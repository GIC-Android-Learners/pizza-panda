package com.example.pizzapanda.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.pizzapanda.data.PizzaDatabase
import com.example.pizzapanda.data.example.ExampleRoomRepository
import com.example.pizzapanda.domain.usecase.AddExampleUseCase
import com.example.pizzapanda.domain.usecase.ExampleUseCases
import com.example.pizzapanda.domain.usecase.GetExamplesUseCase
import com.example.pizzapanda.presentation.example.ExampleViewModel
import com.example.pizzapanda.presentation.example.components.ExampleScreen
import com.example.pizzapanda.presentation.util.Screen
import com.example.pizzapanda.ui.theme.PizzaPandaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Temp
        val db = Room.databaseBuilder(
            application,
            PizzaDatabase::class.java, PizzaDatabase.DB_NAME
        ).build()

        val repository = ExampleRoomRepository(db.exampleDao())

        val useCases = ExampleUseCases(
            getExamples = GetExamplesUseCase(repository),
            addExample = AddExampleUseCase(repository)
        )
        val viewModel = ExampleViewModel(useCases)

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
                        startDestination = Screen.HomeScreen.route
                    ) {
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
