package com.example.pizzapanda.domain.repository

import com.example.pizzapanda.domain.model.Order

interface OrderRepository {
    suspend fun getCurrentOrder(): Order

    suspend fun getAllOrder(): List<Order>

    suspend fun createOrder(order: Order)

    suspend fun checkout(order: Order)
}