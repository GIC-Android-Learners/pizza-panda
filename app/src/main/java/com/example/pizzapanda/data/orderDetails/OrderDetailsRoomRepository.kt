package com.example.pizzapanda.data.orderDetails

import com.example.pizzapanda.domain.model.Order
import com.example.pizzapanda.domain.repository.OrderDetailsRepository

class OrderDetailsRoomRepository(
    private val orderDetailsDao: OrderDetailsDao
) :
    OrderDetailsRepository {
    override suspend fun updateOrder(order: Order) {
        order.id?.let { orderDetailsDao.deleteByOrderId(it) }

        order.details.forEach { orderDetail ->
            order.id?.let {
                orderDetail.menu.id?.let {
                    orderDetailsDao.insert(
                        OrderDetailsEntity(
                            orderId = order.id,
                            itemId = orderDetail.menu.id,
                            count = orderDetail.count
                        )
                    )
                }
            }
        }
    }
}