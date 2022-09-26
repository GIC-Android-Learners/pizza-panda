package com.example.pizzapanda.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pizzapanda.data.example.ExampleDao
import com.example.pizzapanda.data.example.ExampleEntity

@Database(entities = [ExampleEntity::class], version = 1)
abstract class PizzaDatabase : RoomDatabase() {
    abstract fun exampleDao(): ExampleDao

    companion object {
        const val DB_NAME = "pizza-panda"
    }
}