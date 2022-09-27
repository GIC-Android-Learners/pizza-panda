package com.example.pizzapanda.domain.usecase.admin

import com.example.pizzapanda.domain.model.Menu
import com.example.pizzapanda.domain.repository.MenuRepository

class UpdateMenuUseCase(private val menuRepository: MenuRepository) {
    suspend operator fun invoke(menu: Menu) {
        menuRepository.updateMenu(menu = menu)
    }
}