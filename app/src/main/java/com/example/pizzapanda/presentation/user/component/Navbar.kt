package com.example.pizzapanda.presentation.user.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pizzapanda.R
import com.example.pizzapanda.presentation.user.UserEvent
import com.example.pizzapanda.presentation.user.UserViewModel

@Composable
fun NavBar(viewModel: UserViewModel = hiltViewModel()) {
    Column {
        Text(
            text = "Food Information",
            fontWeight = FontWeight.ExtraBold,
            fontFamily = FontFamily.Monospace,
            fontSize = 26.sp,
            color = Color("#004a4d".toColorInt())
        )
        Row {
            ExtendedFloatingActionButton(
                onClick = {
                    viewModel.onEvent(UserEvent.FlipMenu("pizza"))
                },
                text = { Text("Pizza") },
                modifier = Modifier
                    .size(200.dp, 50.dp)
                    .padding(0.dp, 10.dp, 20.dp, 0.dp),
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.pizza_png_6),
                        contentDescription = "Favorite",
                        modifier = Modifier.size(30.dp,30.dp)
                    )
                },
            )
            ExtendedFloatingActionButton(
                onClick = {
                    viewModel.onEvent(UserEvent.FlipMenu("juice"))
                },
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.juice),
                        contentDescription = "Favorite"
                    )
                },
                text = { Text("Juice") },
                modifier = Modifier
                    .size(200.dp, 50.dp)
                    .padding(0.dp, 10.dp, 20.dp, 0.dp)
            )
            ExtendedFloatingActionButton(
                onClick = {
                    viewModel.onEvent(UserEvent.ToggleOrderModal(true))
                },
                icon = {
                    Icon(
                        Icons.Filled.ShoppingCart,
                        contentDescription = "ShoppingCart"
                    )
                },
                text = { Text("Order") },
                modifier = Modifier
                    .size(200.dp, 50.dp)
                    .padding(0.dp, 10.dp, 20.dp, 0.dp)
            )
            ExtendedFloatingActionButton(
                onClick = {
                    viewModel.onEvent(UserEvent.Checkout)
                },
                text = { Text("CheckOut") },
                icon = {
                    Icon(
                        Icons.Default.ArrowForward,
                        contentDescription = "ArrowForward"
                    )
                },
                modifier = Modifier
                    .size(200.dp, 50.dp)
                    .padding(0.dp, 10.dp, 20.dp, 0.dp)
            )
        }
    }
}