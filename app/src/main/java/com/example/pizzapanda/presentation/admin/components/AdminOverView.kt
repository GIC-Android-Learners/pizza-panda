package com.example.pizzapanda.presentation.admin.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.pizzapanda.presentation.main.components.*

@Composable
fun AdminOverView(goToMain: () -> Unit) {
    Row(modifier = Modifier.clip(shape = RoundedCornerShape(25.dp))) {
        Surface(
            modifier = Modifier.size(400.dp, 750.dp),
        ) {
            CoverImagePartitionFirst(goToMain)
        }
        Surface(
            modifier = Modifier
                .size(1000.dp, 750.dp)
        ) {
            CoverImagePartitionSecond()
        }
    }
}