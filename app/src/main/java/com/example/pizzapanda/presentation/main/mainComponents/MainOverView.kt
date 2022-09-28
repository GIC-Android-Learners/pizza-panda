package com.example.pizzapanda.presentation.main.mainComponents

import android.telecom.Call
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Index
import com.example.pizzapanda.R
import kotlinx.coroutines.launch

@Composable
fun SurfaceControl(goToAdmin: () -> Unit) {
    Row(modifier = Modifier.clip(shape = RoundedCornerShape(25.dp))) {
        Surface(
            modifier = Modifier.size(400.dp, 750.dp),
        ) {
            CoverImagePartition1() {
                goToAdmin()
            }
        }
        Surface(
            modifier = Modifier.size(1000.dp, 750.dp),
        ) { CoverImagePartition2() }
    }
}

@Composable
fun CoverImagePartition1(goToAdmin: () -> Unit) {
    Scaffold(
        floatingActionButton = {
            ScaffoldButtons(goToAdmin, "Admin", "Order")
        }
    ) {
        Surface(modifier = Modifier.background(Color("#ffead1".toColorInt()))) {
            Card(elevation = 20.dp, backgroundColor = Color("#ffead1".toColorInt())) {
                Image(
                    painter = painterResource(R.drawable.cover_3),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(shape = RoundedCornerShape(25.dp)),
                    contentScale = ContentScale.FillHeight,
                )
            }
        }
    }
}

@Composable
fun CoverImagePartition2() {
    Surface(modifier = Modifier.background(Color("#ffead1".toColorInt())))
    {
        Column(
            modifier = Modifier
                .background(Color("#ffead1".toColorInt()))
                .padding(50.dp)
        ) {
            OrderButton()
            Text(
                text = "Pizza Information",
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily.Monospace,
                fontSize = 25.sp,
                color = Color("#004a4d".toColorInt()),
                modifier = Modifier.padding(0.dp, 50.dp, 20.dp, 0.dp)
            )
            LazyRow(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                items(3) {
                    ItemCard()
                }
            }
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(25.dp),
                modifier = Modifier
                    .background(Color.Transparent)
                    .padding(0.dp, 20.dp, 0.dp, 0.dp)
            ) {
                items(3) {
                    Text(
                        text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Bold,
                        fontSize = 10.sp,
                        modifier = Modifier
                            .size(180.dp, 60.dp)
                            .border(2.dp, Color.Cyan, shape = RoundedCornerShape(20.dp))
                            .padding(10.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun ItemCard() {
    val addClick = remember {
        mutableStateOf(false)
    }
    val deleteClick = remember {
        mutableStateOf(false)
    }

    Scaffold(
        bottomBar = {
            BottomAppBar(
                cutoutShape = MaterialTheme.shapes.small.copy(
                    CornerSize(percent = 50)
                ),
                backgroundColor = Color.DarkGray,
                ) {
                    ExtendedFloatingActionButton(
                        onClick = { addClick.value = true  },
                        text = { "" },
                        backgroundColor = Color.DarkGray,
                        modifier = Modifier.size(55.dp),
                        icon = {
                            Icon(
                                Icons.Filled.Edit,
                                tint = Color.White,
                                contentDescription = "Update",
                            )
                        },
                    )
                ItemAddForm(addClick = addClick)


                ExtendedFloatingActionButton(
                        onClick = {
                            deleteClick.value = true
                        },
                        text = { "" },
                        backgroundColor = Color.DarkGray,
                        modifier = Modifier.size(55.dp),
                        icon = {
                            Icon(
                                Icons.Filled.Delete,
                                contentDescription = "Delete",
                                tint = Color.White
                            )
                        },
                    )
                    DeleteItems(deleteClick = deleteClick)
            }
        },
        modifier = Modifier
            .size(200.dp, 200.dp)
            .padding(0.dp, 20.dp, 20.dp, 0.dp)
            .background(Color.Black)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                text = "Veggie Pizza",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp,
                color = Color("#004a4d".toColorInt())
            )
            Image(
                painter = painterResource(R.drawable.cover_3),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(shape = RoundedCornerShape(25.dp))
                    .size(160.dp, 100.dp),
                contentScale = ContentScale.FillBounds,
            )
        }
    }
}

@Composable
fun DeleteItems(deleteClick: MutableState<Boolean>) {
    var name = remember { mutableStateOf("") }
    val taste = remember { mutableStateOf("") }
    val price = remember { mutableStateOf("") }
    if (deleteClick.value === true) {
        AlertDialog(
            onDismissRequest = {
                deleteClick.value = false
            },
            text = {
            },
            confirmButton = {
                ExtendedFloatingActionButton(
                    onClick = {
                        //todoViewModel.add(title.value, detail.value)
                        deleteClick.value = false
                    },
                    text = { Text("Delete") },
                    modifier = Modifier
                        .size(150.dp, 50.dp)
                        .padding(0.dp, 0.dp, 20.dp, 10.dp),
                    icon = {
                        Icon(
                            Icons.Filled.Delete,
                            contentDescription = "Delete"
                        )
                    },
                )
            },
            dismissButton = {
                ExtendedFloatingActionButton(
                    onClick = {
                        deleteClick.value = false
                    },
                    text = { Text("Cancel") },
                    modifier = Modifier
                        .size(150.dp, 50.dp)
                        .padding(0.dp, 0.dp, 20.dp, 10.dp),
                    icon = {
                        Icon(
                            Icons.Filled.Clear,
                            contentDescription = "Cancel"
                        )
                    },
                )
            },
            modifier = Modifier.size(400.dp,100.dp)
        )
    }

}

@Composable
fun ScaffoldButtons(goToAdmin: () -> Unit, btn1: String, btn2: String) {
    Row {
        ExtendedFloatingActionButton(
            text = { Text(btn1) },
            onClick = {
                goToAdmin()
            },
            modifier = Modifier
                .padding(30.dp, 0.dp, 15.dp, 0.dp)
                .size(185.dp, 50.dp)
        )
        ExtendedFloatingActionButton(
            text = { Text(btn2) },
            onClick = {
            },
            modifier = Modifier.size(185.dp, 50.dp)
        )
    }
}

@Composable
fun OrderButton() {
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
                onClick = { },
                text = { Text("Pizza") },
                modifier = Modifier
                    .size(200.dp, 50.dp)
                    .padding(0.dp, 10.dp, 20.dp, 0.dp),
                icon = {
                    Icon(
                        Icons.Filled.Favorite,
                        contentDescription = "Favorite"
                    )
                },
            )
            ExtendedFloatingActionButton(
                onClick = { },
                icon = {
                    Icon(
                        Icons.Filled.Favorite,
                        contentDescription = "Favorite"
                    )
                },
                text = { Text("Juice") },
                modifier = Modifier
                    .size(200.dp, 50.dp)
                    .padding(0.dp, 10.dp, 20.dp, 0.dp)
            )
            ExtendedFloatingActionButton(
                onClick = { /* ... */ },
                icon = {
                    Icon(
                        Icons.Filled.Favorite,
                        contentDescription = "Favorite"
                    )
                },
                text = { Text("Change Order") },
                modifier = Modifier
                    .size(200.dp, 50.dp)
                    .padding(0.dp, 10.dp, 20.dp, 0.dp)
            )
            ExtendedFloatingActionButton(
                onClick = { /* ... */ },
                icon = {
                    Icon(
                        Icons.Filled.Favorite,
                        contentDescription = "Favorite"
                    )
                },
                text = { Text("CheckOut") },
                modifier = Modifier
                    .size(200.dp, 50.dp)
                    .padding(0.dp, 10.dp, 20.dp, 0.dp)
            )
        }
    }

}

@Composable
fun CoverFoodItem(coverFood: CoverFood) {
    Row(
        modifier = Modifier
            .background(Color.Transparent)
            .border(2.dp, Color.Cyan, shape = RoundedCornerShape(20.dp))
    ) {
        Column {
            Image(
                painter = painterResource(coverFood.coverPhoto),
                contentDescription = "baguette_bread",
                Modifier
                    .clip(shape = RoundedCornerShape(100.dp))
                    .size(100.dp)
            )
        }
        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                text = coverFood.foodName,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Light,
                fontSize = 20.sp,
            )
            Text(
                text = coverFood.price,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Light,
                fontSize = 23.sp
            )
        }
    }
}

class CoverFood(val coverPhoto: Int, val foodName: String, val price: String) {
}

