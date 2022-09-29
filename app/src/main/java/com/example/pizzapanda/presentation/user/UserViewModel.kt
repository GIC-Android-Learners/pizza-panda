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
class UserViewModel @Inject constructor(private var pizzaListUseCase: FrontUseCases) :
    ViewModel() {
    private val _userState: MutableState<UserState> = mutableStateOf(UserState(listOf()))
    val userState: State<UserState> = _userState
    private val flag = mutableStateOf("pizza")

    init {
        viewModelScope.launch {
            getAllPizza()
        }
    }

    fun separateFunction(pizzaJuiceFlag: String) {
        flag.value = pizzaJuiceFlag
        viewModelScope.launch {
            getAllPizza()
        }
    }

    private suspend fun getAllPizza() {
        if (flag.value === "pizza") {
            _userState.value = userState.value.copy(
                pizzaList = pizzaListUseCase.getPizzaList()
            )
        } else {
            _userState.value = userState.value.copy(
                pizzaList = pizzaListUseCase.getJuiceList()
            )
        }
    }

    fun onEvent(event: UserEvent) {
        viewModelScope.launch {
            when (event) {
                is UserEvent.createOrder -> {
                    pizzaListUseCase.orderFood(Order(details = listOf()))
                    val currentOrder =  pizzaListUseCase.getCurrentOrder()
                    pizzaListUseCase.updateOrder(currentOrder,)
                   // pizzaListUseCase.orderFood(Order(details = OrderDetails(menu= Menu(name = event.name, price = event.price, category = event.category, meat = event.meat, taste = event.taste), count = event.count)))
                }

            }
        }
    }
}