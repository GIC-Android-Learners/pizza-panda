package com.example.pizzapanda.data.orderDetails

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "order_details")
class OrderDetailsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,

    @ColumnInfo(name = "order_id")
    val orderId: Int,

    @ColumnInfo(name = "item_id")
    val itemId: Int,

    @ColumnInfo(name = "count")
    val count: Int = 1,

    @ColumnInfo(name = "order_time", defaultValue = "CURRENT_TIMESTAMP")
    val orderTime: String? = ""
)