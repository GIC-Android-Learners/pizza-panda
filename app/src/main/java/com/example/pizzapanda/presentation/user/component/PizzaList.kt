/*
作成者  : ZMW
作成日  : 2022/09/26
*/

package com.example.pizzapanda.presentation.user.components

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pizzapanda.R
import com.example.pizzapanda.presentation.user.UserViewModel
import kotlinx.coroutines.flow.MutableStateFlow

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PizzaList(viewModel: UserViewModel = hiltViewModel(), pizzaJuiceFlag: String) {
    viewModel.separateFunction(pizzaJuiceFlag)
    val _noteList = remember { MutableStateFlow(listOf<FoodOrder>()) }
    val noteList by remember { _noteList }.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
            .padding(0.dp, 20.dp, 0.dp, 0.dp),
        verticalArrangement = Arrangement.Top
    ) {


        LazyVerticalGrid(
            //5 cards show in 1 line
            cells = GridCells.Fixed(4),
            contentPadding = PaddingValues(8.dp)
        ) {
            //pizza list
            items(viewModel.userState.value.pizzaList.size) { menu ->
                val qtyAssign = 1
                val paddingModifier = Modifier.padding(10.dp)
                Box(modifier = paddingModifier) {
                    val textPadding = 39.dp       //pizza image align
                    val overlayBoxHeight = 25.dp  //image show over the top of card
                    val addClick = remember {      //if card click state value
                        mutableStateOf(false)
                    }
                    Card(
                        elevation = 10.dp,
                        shape = RoundedCornerShape(18.dp), //Card CornerRound
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                addClick.value = true   //if card click state value change
                                val newList = ArrayList(noteList)
                                newList.add(
                                    FoodOrder(
                                        addClick.value,
                                        viewModel.userState.value.pizzaList[menu].name,
                                        viewModel.userState.value.pizzaList[menu].price,
                                        qtyAssign
                                    )
                                )
                                if (newList.size >= 1) {
                                    _noteList.value = newList
                                }
                            },
                        backgroundColor = Color.DarkGray //Card Background
                    ) {
                        var flag = 1
                        //pizza info
                        Column(
                            modifier = paddingModifier, //align for each card
                        ) {
                            if (pizzaJuiceFlag === "pizza") {
                                PizzaName(viewModel.userState.value.pizzaList[menu].name) //Pizza Name
                                Taste(viewModel.userState.value.pizzaList[menu].taste)  //Pizza Taste
                                Taste(viewModel.userState.value.pizzaList[menu].meat) //Meat
                                Price(viewModel.userState.value.pizzaList[menu].price)  //Price
                            } else {
                                PizzaName(viewModel.userState.value.pizzaList[menu].name)
                                Price(viewModel.userState.value.pizzaList[menu].price)
                            }

                        }
                        //for pizza oder
                        if (addClick.value) {
                            FoodOrderModel(
                                addClick.value,
                                viewModel.userState.value.pizzaList[menu].name,
                                viewModel.userState.value.pizzaList[menu].price.toString(),
                                _noteList.value
                            )   //if card click, order modal dialog show
                            if (FoodOrderModel(
                                    addClick.value,
                                    viewModel.userState.value.pizzaList[menu].name,
                                    viewModel.userState.value.pizzaList[menu].price.toString(),
                                    _noteList.value
                                ) === false
                            ) { //if card is closed, card click state value change
                                addClick.value = false
                            }

                        }
                    }
                    //pizza image
                    Box(
                        Modifier
                            .height(90.dp) //pizza image height

                            .width(90.dp)//pizza image width

                            .offset(
                                x = textPadding,
                                y = -overlayBoxHeight
                            )//pizza image show align
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.italian_pizza_on_a_transparent_background__by_prussiaart_dc8zuux_fullview),
                            contentDescription = "gg"
                        )

                    }
                }
            }
        }

    }
}

//ui for name
@Composable
fun PizzaName(name: String) {
    Text(
        name,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(0.dp, 30.dp, 0.dp, 0.dp),
        color = Color.White
    )
}

//ui for taste and meat
@Composable
fun Taste(name: String) {
    Text(
        name,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(10.dp, 10.dp, 0.dp, 0.dp),
        color = Color.White
    )
}

//ui for price
@Composable
fun Price(name: Int) {
    Text(
        name.toString(),
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(10.dp, 10.dp, 0.dp, 0.dp),
        color = Color.White
    )
}

data class FoodOrder(val clickValue: Boolean, val name: String, val price: Int, val qty: Int) {}