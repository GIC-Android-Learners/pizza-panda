package com.example.pizzapanda.presentation.main.mainComponents

import android.net.Uri
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
import com.example.pizzapanda.domain.helper.ImageHelper
import com.example.pizzapanda.domain.model.Menu
import com.example.pizzapanda.domain.storage.FileStorage
import com.example.pizzapanda.domain.storage.util.Directory
import com.example.pizzapanda.presentation.admin.AdminEvent
import com.example.pizzapanda.presentation.admin.AdminViewModel
import com.example.pizzapanda.presentation.util.components.PhotoPicker
import com.example.pizzapanda.storage.LocalFileStorage
import java.io.File

var pizzaBtnClick = mutableStateOf(false)
var juiceBtnClick = mutableStateOf(false)
var allMenuBtnClick = mutableStateOf(true)

@Composable
fun CoverImagePartitionFirst(goToMain: () -> Unit) {
    Scaffold(
        floatingActionButton = {
            ItemScaffoldButton(goToMain,"Back")
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
fun ItemScaffoldButton(goToMain: () -> Unit,btn1: String) {
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
                icon = { Icon(
                    Icons.Filled.ExitToApp,
                    contentDescription = ""
                )},
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
fun ItemCard(menu: Menu, onDelete: (Menu) -> Unit) {
    val addClick = remember {
        mutableStateOf(false)
    }
    val deleteClick = remember {
        mutableStateOf(false)
    }
    val fileStorage: FileStorage = LocalFileStorage(LocalContext.current)
    val photoFile = fileStorage.getFile(Directory.Image.path, menu.photo)

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
                ItemAddForm(addClick = addClick, editingMenu = menu)
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
                DeleteItems(deleteClick = deleteClick) {
                    onDelete(menu)
                }
            }
        },
        modifier = Modifier
            .size(200.dp, 200.dp)
            .padding(0.dp, 20.dp, 20.dp, 0.dp)
            .background(Color.Black)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                text = menu.name,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp,
                color = Color("#004a4d".toColorInt())
            )
            val bitMapImage = ImageHelper.toImageBitMap(photoFile)
            if (bitMapImage === null) {
                Image(
                    painter = painterResource(R.drawable.cover_3),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(shape = RoundedCornerShape(25.dp))
                        .size(160.dp, 100.dp),
                    contentScale = ContentScale.FillBounds,
                )
            } else {
                Image(
                    bitmap = bitMapImage,
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
                ItemCard(data[menuList]) {
                    adminViewModel.onEvent(AdminEvent.DeleteMenu(it))
                }
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
                ItemCard(data[menuList]) {
                    adminViewModel.onEvent(AdminEvent.DeleteMenu(it))
                }
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
                ItemCard(data[menuList]) {
                    adminViewModel.onEvent(AdminEvent.DeleteMenu(it))
                }
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
    photoUri: MutableState<Uri?>,
    originalPhoto: File? = null
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
        PhotoPicker(originalPhoto = originalPhoto, onChoose = {
            photoUri.value = it
        })
    }
}

@Composable
fun JuiceListForm(
    name: MutableState<String>,
    price: MutableState<String>,
    category: MutableState<String>,
    photoUri: MutableState<Uri?>,
    originalPhoto: File? = null
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
        PhotoPicker(originalPhoto = originalPhoto, onChoose = {
            photoUri.value = it
        })
    }
}

@Composable
fun ItemAddForm(
    adminViewModel: AdminViewModel = hiltViewModel(),
    addClick: MutableState<Boolean>,
    editingMenu: Menu? = null
) {
    var isEditing = false
    val name = remember { mutableStateOf("") }
    val taste = remember { mutableStateOf("") }
    val price = remember { mutableStateOf("") }
    val category = remember { mutableStateOf("") }
    val photoUri = remember {
        mutableStateOf<Uri?>(null)
    }
    var originalPhoto: File? = null
    editingMenu?.let {
        name.value = editingMenu.name
        taste.value = editingMenu.taste
        price.value = editingMenu.price.toString()
        category.value = editingMenu.category
        val fileStorage: FileStorage = LocalFileStorage(LocalContext.current)
        originalPhoto = fileStorage.getFile(Directory.Image.path, editingMenu.photo)
        isEditing = true
    }

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
                        text = if (isEditing) {
                            "Item Edit Form"
                        } else {
                            "Item Insert Form"
                        },
                        fontFamily = FontFamily.Monospace
                    )
                    val radioOptions = listOf("pizza", "juice")
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
                    if (selectedOption == "pizza") {
                        category.value = selectedOption
                        PizzaListForm(name, price, taste, category, photoUri, originalPhoto)
                    } else {
                        category.value = selectedOption
                        JuiceListForm(name, price, category, photoUri, originalPhoto)
                    }
                }
            },
            confirmButton = {
                ExtendedFloatingActionButton(
                    onClick = {
                        if (editingMenu != null) {
                            adminViewModel.onEvent(
                                AdminEvent.UpdateMenu(
                                    menu = Menu(
                                        id = editingMenu.id,
                                        name = name.value,
                                        price = price.value.toInt(),
                                        category = category.value,
                                        taste = taste.value
                                    ),
                                    photoUri = photoUri.value
                                )
                            )
                        } else {
                            adminViewModel.onEvent(
                                AdminEvent.InsertMenu(
                                    menu = Menu(
                                        name = name.value,
                                        price = price.value.toInt(),
                                        category = category.value,
                                        taste = taste.value
                                    ),
                                    photoUri = photoUri.value
                                )
                            )
                        }
                        name.value = ""
                        price.value = ""
                        category.value = ""
                        taste.value = ""
                        photoUri.value = null
                        addClick.value = false
                    },
                    text = {
                        Text(
                            if (isEditing) {
                                "Edit"
                            } else {
                                "Add"
                            }
                        )
                    },
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