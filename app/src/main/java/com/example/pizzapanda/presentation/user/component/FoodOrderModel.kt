/*
作成者  : ZMW
作成日  : 2022/09/26
*/

package com.example.pizzapanda.presentation.user.components

import android.annotation.SuppressLint
import android.util.Log
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
import com.example.pizzapanda.domain.model.Menu
import com.example.pizzapanda.domain.model.OrderDetails
import com.example.pizzapanda.presentation.user.UserEvent
import com.example.pizzapanda.presentation.user.UserViewModel


@SuppressLint("StateFlowValueCalledInComposition")
@Composable

fun FoodOrderModel(
    openDialog: Boolean,
    name: String,
    price: String,
    foodOrder: List<OrderDetails>,
    userViewModel: UserViewModel = hiltViewModel()
): Boolean {
    Log.d("zin mar win ----->",foodOrder.size.toString())
    val openDialog = remember {
        mutableStateOf(openDialog)
    }    //alert dialog state value
    //if alert dialog state value is true, dialog show
    var orgPrice = price
    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            title = {
                Text(text = "Your order")
            },
            text = {
                val boxHeightCard = 10.dp
                val boxHeight = 7.dp
                Column() {
                    for (order in foodOrder) {
                        var price = remember { mutableStateOf(order.price) }
                        var itemCount = remember { mutableStateOf(1) }
                        var addBtn = remember { mutableStateOf(false) }
                        var subBtn = remember { mutableStateOf(false) }
                        Row() {
                            Column(modifier = Modifier.width(Dp(230f))) {
                                Text(order.name, fontSize = 15.sp)
                            }

                            Column(
                                modifier = Modifier
                                    .width(Dp(130f))
                                    .offset(x = Dp(0f), y = -boxHeightCard)
                            ) {
                                Row() {
                                    Column(modifier = Modifier.width(Dp(40f))) {
                                        Text(
                                            text = "-",
                                            fontSize = 30.sp,
                                            modifier = Modifier.clickable { subBtn.value = true })
                                    }
                                    Column(
                                        modifier = Modifier
                                            .width(Dp(40f))
                                            .offset(x = Dp(0f), y = boxHeight)
                                    ) {
                                        Text(text = itemCount.value.toString(), fontSize = 20.sp)
                                    }

                                    Column(modifier = Modifier.width(Dp(40f))) {
                                        Text(
                                            text = "+",
                                            fontSize = 30.sp,
                                            modifier = Modifier.clickable { addBtn.value = true })
                                    }
                                    if (addBtn.value) {
                                         itemCount.value++
                                        price.value =
                                            (orgPrice.toInt() * itemCount.value.toInt())
                                        addBtn.value = false
                                    }
                                    if (subBtn.value && itemCount.value.toInt() > 1) {
                                        itemCount.value--
                                        price.value =
                                            (orgPrice.toInt() * itemCount.value.toInt())
                                        subBtn.value = false
                                    }

                                }
                            }

                            Column(modifier = Modifier.width(Dp(100f))) {
                                Text(price.value.toString(), fontSize = 15.sp)
                            }

                        }
                    }
                    Divider(color = Color.Blue, thickness = 1.dp)
                }

            },
            //Order
            confirmButton = {
                Button(
                    onClick = {
                        openDialog.value = false
                        userViewModel.onEvent(UserEvent.createOrder())
                    }) {
                    Text("Order")
                }
            },
            //alert close
            dismissButton = {
                Button(
                    onClick = {
                        openDialog.value = false
                    }) {
                    Text("Close")
                }
            }
        )

    }

    val returnValue = openDialog.value;
    return returnValue;

}


