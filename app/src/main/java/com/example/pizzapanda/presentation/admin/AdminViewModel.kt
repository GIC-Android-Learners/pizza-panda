package com.example.pizzapanda.presentation.admin

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pizzapanda.domain.model.Menu
import com.example.pizzapanda.domain.usecase.admin.AdminUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminViewModel @Inject constructor(private val getMenuUseCases: AdminUseCases): ViewModel(){
    private val _adminState: MutableState<AdminState> = mutableStateOf(AdminState(listOf(), listOf(),listOf(), Menu()))
    val adminState: State<AdminState> = _adminState

    init {
        getAllPizza()
    }

    private  fun getAllPizza(){
        viewModelScope.launch {
            _adminState.value = adminState.value.copy(
                itemList =  getMenuUseCases.getMenuList()
            )
            _adminState.value = adminState.value.copy(
                pizzaListByCategory =  getMenuUseCases.getMenuListByCategory("Pizza")
            )
            _adminState.value = adminState.value.copy(
                juiceListByCategory =  getMenuUseCases.getMenuListByCategory("Juice")
            )
        }
    }


    fun onEvent(event: AdminEvent) {
        viewModelScope.launch {
            when(event) {
                is AdminEvent.InsertPizza -> {
                    getMenuUseCases.insertMenu(Menu(null,event.name,event.price,event.category,event.taste))
                    getAllPizza()
                }

            }
        }
    }
}