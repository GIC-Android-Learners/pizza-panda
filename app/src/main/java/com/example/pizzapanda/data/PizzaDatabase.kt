package com.example.pizzapanda.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pizzapanda.data.example.ExampleDao
import com.example.pizzapanda.data.example.ExampleEntity
import com.example.pizzapanda.data.menu.MenuDao
import com.example.pizzapanda.data.menu.MenuEntity
import com.example.pizzapanda.data.order.OrderDao
import com.example.pizzapanda.data.order.OrderEntity
import com.example.pizzapanda.data.orderDetails.OrderDetailsDao
import com.example.pizzapanda.data.orderDetails.OrderDetailsEntity

@Database(
    entities = [
        ExampleEntity::class,
        MenuEntity::class,
        OrderEntity::class,
        OrderDetailsEntity::class
    ],
    version = 1
)
abstract class PizzaDatabase : RoomDatabase() {
    abstract fun exampleDao(): ExampleDao

    abstract fun menuDao(): MenuDao

    abstract fun orderDao(): OrderDao

    abstract fun orderDetailsDao(): OrderDetailsDao

    companion object {
        const val DB_NAME = "pizza-panda"
    }
}