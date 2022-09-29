package com.example.pizzapanda.presentation.admin.adminComponents

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.pizzapanda.presentation.main.mainComponents.*

@Composable
fun AdminOverView(goToInsert: () -> Unit) {
    Row(modifier = Modifier.clip(shape = RoundedCornerShape(25.dp))) {
        Surface(
            modifier = Modifier.size(400.dp, 750.dp),
        ) {
            CoverImagePartitionFirst()
        }
        Surface(
            modifier = Modifier
                .size(1000.dp, 750.dp)
        ) {
            CoverImagePartitionSecond()
        }
    }
}