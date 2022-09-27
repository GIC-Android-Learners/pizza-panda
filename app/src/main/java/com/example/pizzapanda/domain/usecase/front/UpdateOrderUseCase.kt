package com.example.pizzapanda.domain.usecase.front

import com.example.pizzapanda.domain.model.Order
import com.example.pizzapanda.domain.model.OrderDetails
import com.example.pizzapanda.domain.repository.OrderDetailsRepository

class UpdateOrderUseCase(private val orderDetailsRepository: OrderDetailsRepository) {
    suspend operator fun invoke(order: Order, orderDetails: List<OrderDetails>) {
        return orderDetailsRepository.updateOrder(order, orderDetails)
    }
}