package com.example.pizzapanda.data.order

import com.example.pizzapanda.data.menu.MenuDao
import com.example.pizzapanda.data.menu.MenuEntity
import com.example.pizzapanda.data.orderDetails.OrderDetailsDao
import com.example.pizzapanda.data.orderDetails.OrderDetailsEntity
import com.example.pizzapanda.domain.model.Menu
import com.example.pizzapanda.domain.model.Order
import com.example.pizzapanda.domain.model.OrderDetails
import com.example.pizzapanda.domain.repository.OrderRepository

class OrderRoomRepository(
    private val menuDao: MenuDao,
    private val orderDao: OrderDao,
    private val orderDetailsDao: OrderDetailsDao
) : OrderRepository {

    override suspend fun getCurrentOrder(): Order {
        val orderEntity = orderDao.getCurrentOrder()

        return Order(
            orderEntity.id,
            listOf(),
            orderEntity.isCheckedOut
        )
    }

    override suspend fun getAllOrder(): List<Order> {
        return orderDao.getAllOrders().map {
            toOrder(it)
        }
    }

    override suspend fun createOrder(order: Order) {
        orderDao.insert(
            OrderEntity(
                id = order.id
            )
        )
    }

    override suspend fun checkout(order: Order) {
        if (order.id is Int) {
            orderDao.checkout(order.id)
        }
    }

    private suspend fun toOrder(orderEntity: OrderEntity): Order {
        val orderDetails = orderEntity.id?.let {
            orderDetailsDao.getByOrderId(it).map { orderDetailsEntity ->
                toOrderDetails(orderDetailsEntity)
            }
        }
        return Order(
            id = orderEntity.id,
            details = orderDetails.orEmpty(),
            isCheckedOut = orderEntity.isCheckedOut
        )
    }

    private suspend fun toOrderDetails(orderDetailsEntity: OrderDetailsEntity): OrderDetails =
        OrderDetails(
            id = orderDetailsEntity.id,
            orderId = orderDetailsEntity.orderId,
            menu = toMenu(menuDao.getMenusById(orderDetailsEntity.itemId)),
            orderTime = orderDetailsEntity.orderTime
        )

    private fun toMenu(menuEntity: MenuEntity): Menu = Menu(
        id = menuEntity.id,
        name = menuEntity.name,
        price = menuEntity.price,
        category = menuEntity.category,
        meat = menuEntity.meat,
        taste = menuEntity.taste,
        photo = menuEntity.photo
    )
}