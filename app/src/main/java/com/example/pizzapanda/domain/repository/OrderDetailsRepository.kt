package com.example.pizzapanda.domain.repository

import com.example.pizzapanda.domain.model.Order
import com.example.pizzapanda.domain.model.OrderDetails

interface OrderDetailsRepository {
    suspend fun updateOrder(order: Order, orderDetails: List<OrderDetails>)
}