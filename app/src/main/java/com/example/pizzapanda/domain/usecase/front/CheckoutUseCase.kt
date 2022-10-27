package com.example.pizzapanda.domain.usecase.front

import com.example.pizzapanda.domain.model.Menu
import com.example.pizzapanda.domain.model.Order
import com.example.pizzapanda.domain.repository.OrderRepository

class CheckoutUseCase(private val orderRepository: OrderRepository) {
    suspend operator fun invoke(order: Order) {
        orderRepository.checkout(order)
    }
}