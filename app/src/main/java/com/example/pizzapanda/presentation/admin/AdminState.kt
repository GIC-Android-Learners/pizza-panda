package com.example.pizzapanda.presentation.admin

import com.example.pizzapanda.domain.model.Menu

data class AdminState(
    val itemList: List<Menu>,
    val pizzaListByCategory: List<Menu>,
    val juiceListByCategory: List<Menu>,
    val item: Menu
) {}