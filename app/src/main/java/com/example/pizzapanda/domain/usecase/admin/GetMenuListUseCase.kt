package com.example.pizzapanda.domain.usecase.admin

import com.example.pizzapanda.domain.repository.MenuRepository

class GetMenuListUseCase(private val menuRepository: MenuRepository) {
    suspend operator fun invoke() {
        menuRepository.getMenus()
    }
}