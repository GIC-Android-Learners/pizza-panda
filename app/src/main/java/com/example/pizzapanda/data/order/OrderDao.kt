package com.example.pizzapanda.data.order

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface OrderDao {
    @Query("SELECT * FROM orders")
    suspend fun getAllOrders(): List<OrderEntity>

    @Query("SELECT * FROM orders WHERE is_checked_out = 0 LIMIT 1")
    suspend fun getCurrentOrder(): OrderEntity

    @Query("UPDATE orders SET is_checked_out = 1 WHERE id = :id")
    suspend fun checkout(id: Int)

    @Insert
    suspend fun insert(order: OrderEntity)
}