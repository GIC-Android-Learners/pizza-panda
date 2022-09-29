package com.example.pizzapanda.presentation.user

import com.example.pizzapanda.domain.model.Menu
import com.example.pizzapanda.domain.model.Order

data class UserState(
    val pizzaList: List<Menu>,
    val juiceList: List<Menu>,
    val currentOrder: Order = Order(details = listOf()),
    val selectedCategory: String = "pizza",
    val isShowOrder: Boolean = false
)

