package com.example.pizzapanda.presentation.main.mainComponents

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.example.pizzapanda.R

@Composable
fun MainElements() {

}

@Composable
fun CoverImagePartitionFirst(goToAdmin: () -> Unit) {
    Scaffold(
        floatingActionButton = {
            ScaffoldButtons(goToAdmin, "Pizza", "Juice")
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
fun CoverImagePartitionSecond() {
    Surface(modifier = Modifier.background(Color("#ffead1".toColorInt())))
    {
        Column(
            modifier = Modifier
                .background(Color("#ffead1".toColorInt()))
                .padding(50.dp)
        ) {
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) { WelcomeText() }
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) { PizzaList() }
        }
    }
}

@Composable
fun WelcomeText() {
    Text(
        text = "Welcome !!!",
        color = MaterialTheme.colors.primarySurface,
        fontSize = 40.sp,
        fontStyle = FontStyle.Italic,
        fontWeight = FontWeight.ExtraBold,
        fontFamily = FontFamily.Monospace,
        letterSpacing = 2.sp,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(0.dp, 0.dp, 50.dp, 0.dp)
    )
    AddButton()
}

@Composable
fun AddButton() {
    val addClick = remember {
        mutableStateOf(false)
    }
    ExtendedFloatingActionButton(
        onClick = {
            addClick.value = true
        },
        text = { Text("Add") },
        modifier = Modifier
            .size(200.dp, 50.dp)
            .padding(0.dp, 10.dp, 20.dp, 0.dp),
        icon = {
            Icon(
                Icons.Filled.AddCircle,
                contentDescription = "Favorite"
            )
        },

        )
    ItemAddForm(addClick = addClick)
}


@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun PizzaList() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp, 20.dp, 0.dp, 0.dp)
            .clip(shape = RoundedCornerShape(18.dp)),
        verticalArrangement = Arrangement.Top
    ) {
        val data = listOf("☕", "🙂", "🥛", "🎉", "📐", "🥛", "🎉", "📐")
        LazyVerticalGrid(
            cells = GridCells.Fixed(4),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(data.size) {
                ItemCard()
            }
        }
    }
}

@Composable
fun PizzaName(name: String) {
    Text(
        name,
        textAlign = TextAlign.Justify,
        modifier = Modifier.padding(0.dp, 30.dp, 0.dp, 0.dp),
        color = Color.Black
    )
}

@Composable
fun Taste(name: String) {
    Text(
        name,
        textAlign = TextAlign.Justify,
        modifier = Modifier.padding(10.dp, 5.dp, 0.dp, 0.dp),
        color = Color.Black
    )
}

@Composable
fun Price(name: String) {
    Text(
        name,
        textAlign = TextAlign.Justify,
        modifier = Modifier.padding(10.dp, 5.dp, 0.dp, 0.dp),
        color = Color.Black
    )
}

@Composable
fun ItemAddForm(addClick: MutableState<Boolean>) {
    var name = remember { mutableStateOf("") }
    val taste = remember { mutableStateOf("") }
    val price = remember { mutableStateOf("") }
    if (addClick.value === true) {
        AlertDialog(
            onDismissRequest = {
                addClick.value = false
            },
            text = {
                Column(verticalArrangement = Arrangement.SpaceEvenly) {
                    OutlinedTextField(
                        value = name.value,
                        onValueChange = { name.value = it },
                        label = { Text("Name") },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color(0xe0099db6),
                            unfocusedBorderColor = Color.Gray,
                            focusedLabelColor = Color(0xe0099db6),
                            unfocusedLabelColor = Color.Gray
                        )
                    )
                    OutlinedTextField(
                        value = taste.value,
                        onValueChange = { taste.value = it },
                        label = { Text("Price") },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color(0xe0099db6),
                            unfocusedBorderColor = Color.Gray,
                            focusedLabelColor = Color(0xe0099db6),
                            unfocusedLabelColor = Color.Gray
                        )
                    )
                    OutlinedTextField(
                        value = price.value,
                        onValueChange = { price.value = it },
                        label = { Text("Price") },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color(0xe0099db6),
                            unfocusedBorderColor = Color.Gray,
                            focusedLabelColor = Color(0xe0099db6),
                            unfocusedLabelColor = Color.Gray
                        )
                    )
                }
            },
            confirmButton = {
                ExtendedFloatingActionButton(
                    onClick = {
                        //todoViewModel.add(title.value, detail.value)
                        addClick.value = false
                    },
                    text = { Text("Add") },
                    modifier = Modifier
                        .size(150.dp, 50.dp)
                        .padding(0.dp, 0.dp, 20.dp, 10.dp),
                    icon = {
                        Icon(
                            Icons.Filled.AddCircle,
                            contentDescription = "Add"
                        )
                    },
                )
            },
            dismissButton = {
                ExtendedFloatingActionButton(
                    onClick = {
                        addClick.value = false
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
            modifier = Modifier.size(400.dp,300.dp)
        )
    }
}
