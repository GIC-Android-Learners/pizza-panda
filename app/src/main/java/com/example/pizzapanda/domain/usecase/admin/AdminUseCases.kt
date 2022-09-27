package com.example.pizzapanda.domain.usecase.admin

class AdminUseCases(
    val insertMenu: InsertMenuUseCase,
    val updateMenu: UpdateMenuUseCase,
    val deleteMenu: DeleteMenuUseCase,
    val getMenuList: GetMenuListUseCase,
    val getOrderList: GetOrderListUseCase
)