package com.example.pizzapanda.domain.usecase.front

import com.example.pizzapanda.domain.model.Menu
import com.example.pizzapanda.domain.repository.MenuRepository

class GetPizzaListUseCase(private val menuRepository: MenuRepository) {
    suspend operator fun invoke(): List<Menu> {
        return menuRepository.getMenusByCategory(category = "pizza")
    }
}