package com.example.pizzapanda.domain.usecase.admin

import com.example.pizzapanda.domain.model.Order
import com.example.pizzapanda.domain.repository.OrderRepository

class GetOrderListUseCase(private val orderRepository: OrderRepository) {
    suspend operator fun invoke(): List<Order> {
        return orderRepository.getAllOrder()
    }
}