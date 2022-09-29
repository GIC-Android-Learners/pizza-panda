package com.example.pizzapanda.presentation.user.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pizzapanda.presentation.user.UserEvent
import com.example.pizzapanda.presentation.user.UserViewModel

@Composable
fun UserPane(viewModel: UserViewModel = hiltViewModel()) {
    Column(
        modifier = Modifier
            .background(Color("#ffead1".toColorInt()))
            .padding(50.dp)
    ) {
        NavBar()
        Text(
            text = "Pizza Information",
            fontWeight = FontWeight.ExtraBold,
            fontFamily = FontFamily.Monospace,
            fontSize = 25.sp,
            color = Color("#004a4d".toColorInt()),
            modifier = Modifier.padding(0.dp, 50.dp, 20.dp, 0.dp)
        )
        FoodOrderModal()
        MenuList {
            viewModel.onEvent(UserEvent.AddItem(it))
            viewModel.onEvent(UserEvent.ToggleOrderModal(true))
        }
    }
}