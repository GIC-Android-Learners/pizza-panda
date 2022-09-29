package com.example.pizzapanda.presentation.admin

sealed class AdminEvent {
    data class InsertPizza(val name: String, val price: Int, val category: String, val taste: String): AdminEvent()
    data class UpdatePizza(val name: String, val price: Int, val category: String, val taste: String): AdminEvent()
    data class DeletePizza(val id: Int): AdminEvent()
    data class GetMenuByCategory(val category: String): AdminEvent()

}