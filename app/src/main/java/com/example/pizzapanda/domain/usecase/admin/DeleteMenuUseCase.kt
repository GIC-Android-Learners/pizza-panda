package com.example.pizzapanda.domain.usecase.admin

import com.example.pizzapanda.domain.model.Menu
import com.example.pizzapanda.domain.repository.MenuRepository
import com.example.pizzapanda.domain.storage.FileStorage
import com.example.pizzapanda.domain.storage.util.Directory

class DeleteMenuUseCase(
    private val menuRepository: MenuRepository,
    private val fileStorage: FileStorage
) {
    suspend operator fun invoke(menu: Menu) {
        val oldFileName = menu.photo
        fileStorage.deleteFile(Directory.Image.path, oldFileName)
        menuRepository.deleteMenu(menu = menu)
    }
}