package com.example.pizzapanda.presentation.user

import com.example.pizzapanda.domain.model.Menu

sealed class UserEvent {
    data class createOrder(
        val menu: List<Menu>,
        val count :Int
    ) : UserEvent()
}