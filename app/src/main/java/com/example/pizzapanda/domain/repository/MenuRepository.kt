package com.example.pizzapanda.domain.repository

import com.example.pizzapanda.domain.model.Menu

interface MenuRepository {
    suspend fun getMenus(): List<Menu>

    suspend fun getMenusByCategory(category: String): List<Menu>

    suspend fun insertMenu(menu: Menu)

    suspend fun updateMenu(menu: Menu)

    suspend fun deleteMenu(menu: Menu)
}