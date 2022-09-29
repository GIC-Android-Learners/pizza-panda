package com.example.pizzapanda.domain.usecase.admin

import com.example.pizzapanda.domain.model.Menu
import com.example.pizzapanda.domain.repository.MenuRepository

class GetMenuListByCategoryUseCase(private var menuRepository: MenuRepository) {
    suspend operator fun invoke(category: String): List<Menu> {
        return menuRepository.getMenusByCategory(category)
    }
}