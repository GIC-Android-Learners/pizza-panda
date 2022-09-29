package com.example.pizzapanda.presentation.user.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pizzapanda.domain.model.Order
import com.example.pizzapanda.domain.model.OrderDetails
import com.example.pizzapanda.presentation.user.UserEvent
import com.example.pizzapanda.presentation.user.UserViewModel

@Composable
fun FoodOrderModal(userViewModel: UserViewModel = hiltViewModel()) {
    val currentOrder = userViewModel.userState.value.currentOrder
    val openDialog = userViewModel.userState.value.isShowOrder
    if (openDialog) {
        AlertDialog(
            onDismissRequest = {
                userViewModel.onEvent(UserEvent.ToggleOrderModal(false))
            },
            title = {
                Text(text = "Your orders")
            },
            text = {
                Column {
                    if (currentOrder.details.isEmpty()) {
                        Text(text = "There is no item.")
                    }
                    for (orderDetails in currentOrder.details) {
                        OrderDetails(orderDetails) { amount ->
                            userViewModel.onEvent(UserEvent.ChangeAmount(orderDetails.menu, amount))
                        }
                    }
                    Divider(color = Color.Blue, thickness = 1.dp)
                }
            },
            confirmButton = {
                if (currentOrder.details.isNotEmpty()) {
                    Button(
                        onClick = {
                            userViewModel.onEvent(UserEvent.ToggleOrderModal(false))
                            userViewModel.onEvent(
                                UserEvent.CreateOrder(
                                    Order(
                                        details = listOf()
                                    )
                                )
                            )
                        }) {
                        Text("Order")
                    }
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        userViewModel.onEvent(UserEvent.ToggleOrderModal(false))
                    }) {
                    Text("Close")
                }
            }
        )
    }
}

@Composable
fun OrderDetails(
    orderDetails: OrderDetails,
    onAmountChange: (Int) -> Unit
) {
    val menu = orderDetails.menu
    val count = orderDetails.count

    Row {
        Column(modifier = Modifier.width(Dp(230f))) {
            Text(menu.name, fontSize = 15.sp)
        }
        Column(
            modifier = Modifier
                .width(Dp(130f))
                .offset(x = Dp(0f), y = (-10).dp)
        ) {
            Row {
                Column(modifier = Modifier.width(Dp(40f))) {
                    Text(
                        text = "-",
                        fontSize = 30.sp,
                        modifier = Modifier.clickable {
                            onAmountChange(-1)
                        }
                    )
                }
                Column(
                    modifier = Modifier
                        .width(Dp(40f))
                        .offset(x = Dp(0f), y = 7.dp)
                ) {
                    Text(
                        text = count.toString(),
                        fontSize = 20.sp
                    )
                }
                Column(modifier = Modifier.width(Dp(40f))) {
                    Text(
                        text = "+",
                        fontSize = 30.sp,
                        modifier = Modifier.clickable {
                            onAmountChange(1)
                        }
                    )
                }
            }
        }
    }
}