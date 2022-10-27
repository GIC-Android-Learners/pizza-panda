package com.example.pizzapanda.domain.model

class OrderDetails(
    val id: Int? = null,
    val orderId: Int? = null,
    val menu: Menu,
    val count: Int = 1,
    val orderTime: String = ""
)