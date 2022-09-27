package com.example.pizzapanda.domain.usecase.admin

import com.example.pizzapanda.domain.model.Menu
import com.example.pizzapanda.domain.repository.MenuRepository

class GetMenuListUseCase(private val menuRepository: MenuRepository) {
    suspend operator fun invoke(): List<Menu> {
        return menuRepository.getMenus()
    }
}