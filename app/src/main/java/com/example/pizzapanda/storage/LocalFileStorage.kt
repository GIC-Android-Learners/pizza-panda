package com.example.pizzapanda.storage

import android.content.Context
import android.util.Log
import com.example.pizzapanda.domain.storage.FileStorage
import java.io.File
import java.io.FileOutputStream

class LocalFileStorage(private var context: Context) : FileStorage {
    override fun getFile(dirname: String, fileName: String): File {
        val directory = context.getDir(dirname, Context.MODE_PRIVATE)
        return File(directory.absoluteFile, fileName)
    }

    override fun getFiles(dir: String): Array<File> {
        val directory = context.getDir(dir, Context.MODE_PRIVATE)
        directory.listFiles()?.let {
            return it
        }
        return arrayOf()
    }

    override fun storeFile(dirname: String, filename: String, data: ByteArray) {
        val directory = context.getDir(dirname, Context.MODE_PRIVATE)
        val file = File(directory.absoluteFile, filename)
        val fileOutputStream = FileOutputStream(file)
        fileOutputStream.write(data)
        fileOutputStream.close()
    }

    override fun deleteFile(dirname: String, filename: String) {
        val directory = context.getDir(dirname, Context.MODE_PRIVATE)
        val file = File(directory.absoluteFile, filename)
        file.delete()
    }
}