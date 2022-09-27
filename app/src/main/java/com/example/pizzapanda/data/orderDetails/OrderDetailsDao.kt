package com.example.pizzapanda.data.orderDetails

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface OrderDetailsDao {
    @Query("SELECT * FROM order_details WHERE order_id = :orderId")
    suspend fun getByOrderId(orderId: Int): List<OrderDetailsEntity>


    @Query("DELETE FROM order_details WHERE order_id = :orderId")
    suspend fun deleteByOrderId(orderId: Int)

    @Insert
    suspend fun insert(orderDetails: OrderDetailsEntity)
}