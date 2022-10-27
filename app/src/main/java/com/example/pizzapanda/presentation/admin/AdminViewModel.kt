package com.example.pizzapanda.presentation.admin

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
class AdminViewModel @Inject constructor(private val adminUseCases: AdminUseCases) : ViewModel() {
    private val _adminState: MutableState<AdminState> =
        mutableStateOf(AdminState(listOf(), listOf(), listOf(), Menu()))
    val adminState: State<AdminState> = _adminState

    init {
        getAllPizza()
    }

    private fun getAllPizza() {
        viewModelScope.launch {
            _adminState.value = adminState.value.copy(
                itemList = adminUseCases.getMenuList()
            )
            _adminState.value = adminState.value.copy(
                pizzaListByCategory = adminUseCases.getMenuListByCategory("Pizza")
            )
            _adminState.value = adminState.value.copy(
                juiceListByCategory = adminUseCases.getMenuListByCategory("Juice")
            )
        }
    }


    fun onEvent(event: AdminEvent) {
        viewModelScope.launch {
            when (event) {
                is AdminEvent.InsertMenu -> {
                    adminUseCases.insertMenu(event.menu, event.photoUri)
                    getAllPizza()
                }
                is AdminEvent.DeleteMenu -> {
                    adminUseCases.deleteMenu(event.menu)
                    getAllPizza()
                }
                is AdminEvent.UpdateMenu -> {
                    adminUseCases.updateMenu(event.menu, event.photoUri)
                    getAllPizza()
                }
            }
        }
    }
}