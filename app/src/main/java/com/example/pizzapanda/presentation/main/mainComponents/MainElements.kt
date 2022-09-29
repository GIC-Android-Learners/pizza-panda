package com.example.pizzapanda.presentation.main.mainComponents

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pizzapanda.R
import com.example.pizzapanda.presentation.admin.AdminEvent
import com.example.pizzapanda.presentation.admin.AdminViewModel

var pizzaBtnClick = mutableStateOf(false)
var juiceBtnClick = mutableStateOf(false)
var allMenuBtnClick = mutableStateOf(true)

@Composable
fun CoverImagePartitionFirst() {
    Scaffold(
        floatingActionButton = {
            ItemScaffoldButton("Pizza List", "Juice List")
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
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                if (pizzaBtnClick.value) {
                    PizzaList()
                }
                if (juiceBtnClick.value) {
                    JuiceList()
                }
                if (allMenuBtnClick.value) {
                    MenuList()
                }
            }
        }
    }
}

@Composable
fun ItemScaffoldButton(btn1: String, btn2: String) {
    val pizzaClick = remember {
        mutableStateOf(false)
    }
    val juiceClick = remember {
        mutableStateOf(false)
    }
    Row {
        Column {
            ExtendedFloatingActionButton(
                text = { Text(btn1) },
                modifier = Modifier
                    .padding(30.dp, 0.dp, 15.dp, 0.dp)
                    .size(185.dp, 50.dp),
                onClick = {
                    pizzaClick.value = true
                    if (pizzaClick.value) {
                        pizzaBtnClick.value = true
                        juiceBtnClick.value = false
                        allMenuBtnClick.value = false
                    }
                },
            )
        }
        Column {
            ExtendedFloatingActionButton(
                text = { Text(btn2) },
                onClick = {
                    juiceClick.value = true
                    if (juiceClick.value) {
                        juiceBtnClick.value = true
                        pizzaBtnClick.value = false
                        allMenuBtnClick.value = false
                    }
                },
                modifier = Modifier.size(185.dp, 50.dp)
            )
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
    val allClick = remember {
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
    ExtendedFloatingActionButton(
        onClick = {
            allClick.value = true
            if (allClick.value) {
                pizzaBtnClick.value = false
                juiceBtnClick.value = false
                allMenuBtnClick.value = true
            }
        },
        text = { Text("All Item List") },
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

@Composable
fun ItemCard(name: String) {
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
                    onClick = { addClick.value = true },
                    text = { },
                    backgroundColor = Color.DarkGray,
                    modifier = Modifier.size(55.dp),
                    icon = {
                        Icon(
                            Icons.Filled.Edit,
                            tint = Color.White,
                            contentDescription = "Update",
                        )
                    }
                )
                ItemAddForm(addClick = addClick)
                ExtendedFloatingActionButton(
                    onClick = {
                        deleteClick.value = true
                    },
                    text = { },
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
                text = name,
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PizzaList(adminViewModel: AdminViewModel = hiltViewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp, 20.dp, 0.dp, 0.dp)
            .clip(shape = RoundedCornerShape(18.dp)),
        verticalArrangement = Arrangement.Top
    ) {
        val data = adminViewModel.adminState.value.pizzaListByCategory
        LazyVerticalGrid(
            cells = GridCells.Fixed(4),
            contentPadding = PaddingValues(8.dp)
        ) {

            items(data.size) { menuList ->
                ItemCard(data[menuList].name)
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun JuiceList(adminViewModel: AdminViewModel = hiltViewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp, 20.dp, 0.dp, 0.dp)
            .clip(shape = RoundedCornerShape(18.dp)),
        verticalArrangement = Arrangement.Top
    ) {
        val data = adminViewModel.adminState.value.juiceListByCategory
        LazyVerticalGrid(
            cells = GridCells.Fixed(4),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(data.size) { menuList ->
                ItemCard(data[menuList].name)
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun MenuList(adminViewModel: AdminViewModel = hiltViewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp, 20.dp, 0.dp, 0.dp)
            .clip(shape = RoundedCornerShape(18.dp)),
        verticalArrangement = Arrangement.Top
    ) {
        val data = adminViewModel.adminState.value.itemList

        LazyVerticalGrid(
            cells = GridCells.Fixed(4),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(data.size) { menuList ->
                ItemCard(data[menuList].name)
            }
        }
    }
}

@Composable
fun PizzaListForm(
    name: MutableState<String>,
    price: MutableState<String>,
    taste: MutableState<String>,
    category: MutableState<String>,
) {
    Column {
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
        OutlinedTextField(
            value = taste.value,
            onValueChange = { taste.value = it },
            label = { Text("Taste") },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xe0099db6),
                unfocusedBorderColor = Color.Gray,
                focusedLabelColor = Color(0xe0099db6),
                unfocusedLabelColor = Color.Gray
            )
        )
    }
}

@Composable
fun JuiceListForm(
    name: MutableState<String>,
    price: MutableState<String>,
    category: MutableState<String>,
) {
    Column {
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
}

@Composable
fun ItemAddForm(
    adminViewModel: AdminViewModel = hiltViewModel(),
    addClick: MutableState<Boolean>,
) {
    val name = remember { mutableStateOf("") }
    val taste = remember { mutableStateOf("") }
    val price = remember { mutableStateOf("") }
    val category = remember { mutableStateOf("") }

    if (addClick.value) {
        AlertDialog(
            onDismissRequest = {
                addClick.value = true
            },
            text = {
                Column(
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(
                        text = "Item Insert Form",
                        fontFamily = FontFamily.Monospace
                    )
                    val radioOptions = listOf("Pizza", "Juice")
                    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }

                    Row {
                        radioOptions.forEach { text ->
                            Row(
                                Modifier
                                    .selectable(
                                        selected = (text == selectedOption),
                                        onClick = { onOptionSelected(text) }
                                    )
                                    .padding(horizontal = 16.dp)
                            ) {
                                val context = LocalContext.current
                                RadioButton(
                                    selected = (text == selectedOption),
                                    modifier = Modifier.padding(all = Dp(value = 8F)),
                                    onClick = {
                                        onOptionSelected(text)
                                        Toast.makeText(context, text, Toast.LENGTH_LONG).show()
                                    }
                                )
                                Text(
                                    text = text,
                                    modifier = Modifier.padding(top = 10.dp)
                                )
                            }
                        }
                    }
                    if (selectedOption == "Pizza") {
                        category.value = selectedOption
                        PizzaListForm(name, price, taste, category)
                    } else {
                        category.value = selectedOption
                        JuiceListForm(name, price, category)
                    }
                }
            },
            confirmButton = {
                ExtendedFloatingActionButton(
                    onClick = {
                        adminViewModel.onEvent(
                            AdminEvent.InsertPizza(
                                name.value,
                                price.value.toInt(), category.value,
                                taste.value
                            )
                        )
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
            modifier = Modifier.size(500.dp, 500.dp)
        )
    }
}