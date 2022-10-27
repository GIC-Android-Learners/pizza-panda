package com.example.pizzapanda.domain.usecase.front

class FrontUseCases(
    val getPizzaList: GetPizzaListUseCase,
    val getJuiceList: GetJuiceListUseCase,
    val getOrderList: GetCurrentOrderUseCase,
    val orderFood: OrderFoodUseCase,
    val updateOrder: UpdateOrderUseCase,
    val checkout: CheckoutUseCase
)