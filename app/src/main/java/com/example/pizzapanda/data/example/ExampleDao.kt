package com.example.pizzapanda.data.example

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ExampleDao {
    @Query("SELECT * FROM examples order by id desc")
    suspend fun getAll(): List<ExampleEntity>

    @Insert
    suspend fun insert(todo: ExampleEntity)
}