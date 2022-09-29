package com.example.pizzapanda.presentation.util.components

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.pizzapanda.domain.helper.ImageHelper
import java.io.File

@Composable
// Show original image on start
// Call onChoose with chosen image's uri upon selection
fun PhotoPicker(originalPhoto: File? = null, onChoose: (Uri) -> Unit) {
    val imageUri = remember { mutableStateOf<Uri?>(null) }
    val bitmap = remember { mutableStateOf<Bitmap?>(null) }

    if (imageUri.value === null) {
        originalPhoto?.let {
            bitmap.value = ImageHelper.toImageBitMap(originalPhoto).asAndroidBitmap()
        }
    } else {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val source = ImageDecoder.createSource(
                LocalContext.current.contentResolver,
                imageUri.value!!
            )
            bitmap.value = ImageDecoder.decodeBitmap(source)
        } else {
            TODO("VERSION.SDK_INT < P")
        }
    }

    bitmap.value?.let { bitmap ->
        Image(
            bitmap = bitmap.asImageBitmap(),
            contentDescription = "",
            modifier = Modifier.width(150.dp)
        )
    }

    val launcher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()
        ) { uri: Uri? ->
            imageUri.value = uri
            uri?.let { onChoose(it) }
        }

    Button(
        onClick = {
            launcher.launch("image/*")
        }) {
        Text("Choose Image")
    }
}
