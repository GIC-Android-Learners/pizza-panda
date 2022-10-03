package com.example.pizzapanda.domain.usecase.admin

import android.app.Application
import android.net.Uri
import com.example.pizzapanda.domain.helper.ImageHelper
import com.example.pizzapanda.domain.model.Menu
import com.example.pizzapanda.domain.repository.MenuRepository
import com.example.pizzapanda.domain.storage.FileStorage
import com.example.pizzapanda.domain.storage.util.Directory

class InsertMenuUseCase(
    private val menuRepository: MenuRepository,
    private val fileStorage: FileStorage,
    private val application: Application,
) {
    suspend operator fun invoke(menu: Menu, imageUri: Uri?) {
        imageUri?.let {
            val imageContent = ImageHelper.toByteArray(application, uri = imageUri)
            val fileName = System.currentTimeMillis().toString()
            imageContent?.let {
                fileStorage.storeFile(Directory.Image.path, fileName, imageContent)
            }

            menuRepository.insertMenu(
                menu = Menu(
                    name = menu.name,
                    price = menu.price,
                    category = menu.category,
                    meat = menu.meat,
                    taste = menu.taste,
                    photo = fileName
                )
            )

            return
        }
        menuRepository.insertMenu(menu)
    }
}