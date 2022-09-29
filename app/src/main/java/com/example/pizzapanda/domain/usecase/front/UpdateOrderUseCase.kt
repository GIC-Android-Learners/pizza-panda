package com.example.pizzapanda.domain.usecase.front

import com.example.pizzapanda.domain.model.Order
import com.example.pizzapanda.domain.repository.OrderDetailsRepository

class UpdateOrderUseCase(private val orderDetailsRepository: OrderDetailsRepository) {
    suspend operator fun invoke(order: Order) {
        return orderDetailsRepository.updateOrder(order)
    }
}