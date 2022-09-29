package com.example.pizzapanda.presentation.user

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
        }
        else
        {
            _userState.value = userState.value.copy(
                pizzaList = pizzaListUseCase.getJuiceList()
            )
        }
    }
}