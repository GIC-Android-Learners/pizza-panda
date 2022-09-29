package com.example.pizzapanda.presentation.user.component

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pizzapanda.R
import com.example.pizzapanda.domain.helper.ImageHelper
import com.example.pizzapanda.domain.model.Menu
import com.example.pizzapanda.domain.storage.FileStorage
import com.example.pizzapanda.domain.storage.util.Directory
import com.example.pizzapanda.presentation.user.UserViewModel
import com.example.pizzapanda.storage.LocalFileStorage

@Composable
fun MenuList(viewModel: UserViewModel = hiltViewModel(), onMenuClick: (Menu) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
            .padding(0.dp, 20.dp, 0.dp, 0.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            val menuList = if (viewModel.userState.value.selectedCategory == "pizza") {
                viewModel.userState.value.pizzaList
            } else {
                viewModel.userState.value.juiceList
            }
            MenuGrid(menus = menuList) { menu ->
                onMenuClick(menu)
            }
        }
    }
}

@Composable
fun MenuItem(menu: Menu, onClick: (Menu) -> Unit) {
    Box(
        modifier = Modifier.padding(10.dp)
    ) {
        Card(
            elevation = 10.dp,
            shape = RoundedCornerShape(18.dp),
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onClick(menu)
                }
        ) {
            Column(
                modifier = Modifier.padding(10.dp)
            ) {
                if (menu.category == "pizza") {
                    Pizza(menu = menu)
                }
                if (menu.category == "juice") {
                    Juice(menu = menu)
                }
            }
        }
    }
}

@Composable
fun Pizza(menu: Menu) {
    Column {
        MenuImage(menu.photo)
        MenuName(name = menu.name)
        Description(text = menu.taste)
        Description(text = menu.meat)
        Description(text = menu.price.toString())
    }
}

@Composable
fun Juice(menu: Menu) {
    Column {
        MenuImage(menu.photo)
        MenuName(name = menu.name)
        Description(text = menu.price.toString())
    }
}

@Composable
fun MenuImage(photo: String) {
    val fileStorage: FileStorage = LocalFileStorage(LocalContext.current)
    val photoFile = fileStorage.getFile(Directory.Image.path, photo)
    val bitMapImage = ImageHelper.toImageBitMap(photoFile)

    Box(
        modifier = Modifier
            .height(90.dp)
            .width(90.dp)
            .offset(
                x = 40.dp,
                y = 24.dp
            )
    ) {
        if (bitMapImage === null) {
            Image(
                painter = painterResource(id = R.drawable.italian_pizza_on_a_transparent_background__by_prussiaart_dc8zuux_fullview),
                contentDescription = "Menu Image"
            )
        } else {
            Image(
                bitmap = bitMapImage,
                contentDescription = "Menu Image"
            )
        }
    }
}

@Composable
fun MenuName(name: String) {
    Text(
        name,
        textAlign = TextAlign.Justify,
        modifier = Modifier.padding(0.dp, 30.dp, 0.dp, 0.dp),
        color = Color.Black
    )
}

@Composable
fun Description(text: String) {
    Text(
        text = text,
        textAlign = TextAlign.Justify,
        modifier = Modifier.padding(10.dp, 5.dp, 0.dp, 0.dp),
        color = Color.Black
    )
}

@Composable
fun MenuGrid(
    menus: List<Menu>,
    onMenuClick: (Menu) -> Unit
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        menus.chunked(4).forEach { rowMenus ->
            Row(
                modifier = Modifier.padding(vertical = 10.dp)
            ) {
                rowMenus.forEach { menu ->
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                            .width(180.dp)
                    ) {
                        MenuItem(menu) { menu ->
                            onMenuClick(menu)
                        }
                    }
                }
            }
        }
    }
}