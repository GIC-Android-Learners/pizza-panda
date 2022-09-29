package com.example.pizzapanda.presentation.main.mainComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import com.example.pizzapanda.R
import com.example.pizzapanda.presentation.user.component.UserPane

@Composable
fun SurfaceControl(
    goToAdmin: () -> Unit
) {
    Row(modifier = Modifier.clip(shape = RoundedCornerShape(25.dp))) {
        Surface(
            modifier = Modifier.size(400.dp, 750.dp),
        ) {
            CoverImagePartition1 {
                goToAdmin()
            }
        }
        Surface(
            modifier = Modifier.size(1000.dp, 750.dp),
        ) {
            CoverImagePartition2()
        }
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
                    painter = painterResource(R.drawable.cover_8),
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
        UserPane()
    }
}

@Composable
fun DeleteItems(deleteClick: MutableState<Boolean>) {
    if (deleteClick.value) {
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
            modifier = Modifier.size(400.dp, 100.dp)
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

