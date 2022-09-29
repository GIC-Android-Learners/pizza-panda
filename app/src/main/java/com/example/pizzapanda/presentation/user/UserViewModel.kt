package com.example.pizzapanda.presentation.user

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pizzapanda.domain.model.Menu
import com.example.pizzapanda.domain.model.Order
import com.example.pizzapanda.domain.model.OrderDetails
import com.example.pizzapanda.domain.usecase.front.FrontUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private var frontUseCases: FrontUseCases) :
    ViewModel() {
    private val _userState: MutableState<UserState> = mutableStateOf(UserState(listOf(), listOf()))
    val userState: State<UserState> = _userState

    init {
        viewModelScope.launch {
            setMenu()
        }
    }

    private suspend fun setMenu() {
        _userState.value = userState.value.copy(
            pizzaList = frontUseCases.getPizzaList(),
            juiceList = frontUseCases.getJuiceList()
        )
    }

    fun onEvent(event: UserEvent) {
        viewModelScope.launch {
            when (event) {
                is UserEvent.CreateOrder -> {
                    createOrder()
                }
                is UserEvent.FlipMenu -> {
                    _userState.value = userState.value.copy(
                        selectedCategory = event.category
                    )
                }
                is UserEvent.AddItem -> {
                    addOrderItem(event.menu)
                }
                is UserEvent.ChangeAmount -> {
                    changeAmount(event.menu, event.amount)
                }
                is UserEvent.ToggleOrderModal -> {
                    _userState.value = userState.value.copy(
                        isShowOrder = event.isShown
                    )
                }
                is UserEvent.Checkout -> {
                    checkout()
                }
            }
        }
    }

    private suspend fun createOrder() {
        if (userState.value.currentOrder.id == null) {
            frontUseCases.orderFood(userState.value.currentOrder)

        } else {
            frontUseCases.updateOrder(userState.value.currentOrder)
        }
        val currentOrderId = frontUseCases.getOrderList().id
        val orderDetails = userState.value.currentOrder.details
        _userState.value = userState.value.copy(
            currentOrder = Order(
                id = currentOrderId,
                details = orderDetails
            )
        )
    }

    private fun addOrderItem(menu: Menu) {
        val currentOrder = userState.value.currentOrder
        val orderedItem = currentOrder.details.firstOrNull {
            it.menu.id === menu.id
        }
        orderedItem?.let {
            changeAmount(menu, 1)
        }

        if (orderedItem == null) {
            addItem(menu)
        }
    }

    private fun changeAmount(menu: Menu, amount: Int) {
        val currentOrder = userState.value.currentOrder
        val orderDetails = currentOrder.details.map {
            if (it.menu.id == menu.id) {
                OrderDetails(
                    id = it.id,
                    orderId = it.orderId,
                    menu = it.menu,
                    count = it.count + amount,
                    orderTime = it.orderTime
                )
            } else {
                it
            }
        }

        _userState.value = userState.value.copy(
            currentOrder = Order(
                id = currentOrder.id,
                details = orderDetails.filter {
                    it.count >= 1
                }
            )
        )
    }

    private fun addItem(menu: Menu) {
        val currentOrder = userState.value.currentOrder
        val orderDetails: MutableList<OrderDetails> = currentOrder.details.toMutableList()
        orderDetails.add(
            OrderDetails(
                menu = menu
            )
        )
        _userState.value = userState.value.copy(
            currentOrder = Order(
                id = currentOrder.id,
                details = orderDetails
            )
        )
    }

    private suspend fun checkout() {
        frontUseCases.checkout(userState.value.currentOrder)
        _userState.value = userState.value.copy(
            currentOrder = Order(details = listOf()),
            selectedCategory = "pizza"
        )
    }
}