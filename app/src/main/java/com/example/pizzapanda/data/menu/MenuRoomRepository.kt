package com.example.pizzapanda.data.menu

import com.example.pizzapanda.domain.model.Menu
import com.example.pizzapanda.domain.repository.MenuRepository

class MenuRoomRepository(private val menuDao: MenuDao) : MenuRepository {
    override suspend fun getMenus(): List<Menu> {
        return menuDao.getMenus().map {
            toMenu(it)
        }
    }

    override suspend fun getMenusByCategory(category: String): List<Menu> {
        return menuDao.getMenusByCategory(category).map {
            toMenu(it)
        }
    }

    override suspend fun insertMenu(menu: Menu) {
        menuDao.insert(toMenuEntity(menu = menu))
    }

    override suspend fun updateMenu(menu: Menu) {
        menuDao.update(toMenuEntity(menu = menu))
    }

    override suspend fun deleteMenu(menu: Menu) {
        menuDao.delete(toMenuEntity(menu = menu))
    }

    private fun toMenuEntity(menu: Menu): MenuEntity = MenuEntity(
        id = menu.id,
        name = menu.name,
        price = menu.price,
        category = menu.category,
        meat = menu.meat,
        taste = menu.taste,
        photo = menu.photo
    )

    private fun toMenu(menuEntity: MenuEntity): Menu = Menu(
        id = menuEntity.id,
        name = menuEntity.name,
        price = menuEntity.price,
        category = menuEntity.category,
        meat = menuEntity.meat,
        taste = menuEntity.taste,
        photo = menuEntity.photo
    )
}