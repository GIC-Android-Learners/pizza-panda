package com.example.pizzapanda.domain.usecase.admin

import com.example.pizzapanda.domain.model.Menu
import com.example.pizzapanda.domain.repository.MenuRepository

class DeleteMenuUseCase(private val menuRepository: MenuRepository) {
    suspend operator fun invoke(menu: Menu) {
        menuRepository.deleteMenu(menu = menu)
    }
}