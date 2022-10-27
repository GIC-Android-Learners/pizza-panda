package com.example.pizzapanda.domain.helper

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import java.io.ByteArrayOutputStream
import java.io.File

class ImageHelper {
    companion object {
        fun toByteArray(context: Context, uri: Uri): ByteArray? =
            toBitMap(context, uri)?.let {
                toByteArray(it)
            }

        fun toImageBitMap(file: File): ImageBitmap? {
            val bitmap = BitmapFactory.decodeFile(file.path)
            return bitmap?.asImageBitmap() ?: run {
                return null
            }
        }

        private fun toBitMap(context: Context, uri: Uri): Bitmap? {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                val source = ImageDecoder.createSource(context.contentResolver, uri)
                return ImageDecoder.decodeBitmap(source)
            }
            return null
        }

        private fun toByteArray(bitmap: Bitmap): ByteArray {
            val bitmapOutputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, bitmapOutputStream)
            return bitmapOutputStream.toByteArray()
        }
    }
}