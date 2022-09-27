package com.example.pizzapanda.domain.model

class Order(
    val id: Int? = null,
    val details: List<OrderDetails>,
    val isCheckedOut: Boolean = false
)