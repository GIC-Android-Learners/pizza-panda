package com.example.pizzapanda.domain.storage

import java.io.File

interface FileStorage {
    fun getFile(dirname: String, fileName: String): File

    fun getFiles(dirname: String): Array<File>

    fun storeFile(dirname: String, filename: String, data: ByteArray)

    fun deleteFile(dirname: String, filename: String)
}