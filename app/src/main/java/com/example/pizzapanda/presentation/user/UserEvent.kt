package com.example.pizzapanda.presentation.user

import com.example.pizzapanda.domain.model.Menu
import com.example.pizzapanda.domain.model.Order

sealed class UserEvent {
    data class CreateOrder(
        val order: Order
    ) : UserEvent()

    data class FlipMenu(
        val category: String
    ) : UserEvent()

    data class AddItem(
        val menu: Menu
    ) : UserEvent()

    data class ChangeAmount(
        val menu: Menu,
        val amount: Int
    ) : UserEvent()

    data class ToggleOrderModal(
        val isShown: Boolean
    ) : UserEvent()

    object Checkout : UserEvent()
}