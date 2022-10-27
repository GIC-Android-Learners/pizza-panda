package com.example.pizzapanda.domain.repository

import com.example.pizzapanda.domain.model.Order

interface OrderDetailsRepository {
    suspend fun updateOrder(order: Order)
}