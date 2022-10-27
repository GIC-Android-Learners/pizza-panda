package com.example.pizzapanda.data.menu

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "menus")
class MenuEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "price")
    val price: Int = 0,

    @ColumnInfo(name = "category")
    val category: String = "",

    @ColumnInfo(name = "meat")
    val meat: String = "",

    @ColumnInfo(name = "taste")
    val taste: String = "",

    @ColumnInfo(name = "photo")
    val photo: String = ""
)