package com.example.pizzapanda.data.menu

import androidx.room.*

@Dao
interface MenuDao {
    @Query("SELECT * FROM menus")
    suspend fun getMenus(): List<MenuEntity>

    @Query("SELECT * FROM menus WHERE id = :menuId")
    suspend fun getMenusById(menuId: Int): MenuEntity

    @Query("SELECT * FROM menus WHERE category = :category")
    suspend fun getMenusByCategory(category: String): List<MenuEntity>

    @Insert
    suspend fun insert(menu: MenuEntity)

    @Update
    suspend fun update(menu: MenuEntity)

    @Delete
    suspend fun delete(menu: MenuEntity)
}