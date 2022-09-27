package com.example.pizzapanda.di

import android.app.Application
import androidx.room.Room
import com.example.pizzapanda.data.PizzaDatabase
import com.example.pizzapanda.data.example.ExampleRoomRepository
import com.example.pizzapanda.data.menu.MenuRoomRepository
import com.example.pizzapanda.data.order.OrderRoomRepository
import com.example.pizzapanda.data.orderDetails.OrderDetailsRoomRepository
import com.example.pizzapanda.domain.repository.ExampleRepository
import com.example.pizzapanda.domain.repository.MenuRepository
import com.example.pizzapanda.domain.repository.OrderDetailsRepository
import com.example.pizzapanda.domain.repository.OrderRepository
import com.example.pizzapanda.domain.usecase.AddExampleUseCase
import com.example.pizzapanda.domain.usecase.ExampleUseCases
import com.example.pizzapanda.domain.usecase.GetExamplesUseCase
import com.example.pizzapanda.domain.usecase.admin.*
import com.example.pizzapanda.domain.usecase.front.*
import com.example.pizzapanda.domain.usecase.front.GetCurrentOrderUseCase
import com.example.pizzapanda.presentation.example.ExampleViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun providePizzaDatabase(application: Application): PizzaDatabase {
        return Room.databaseBuilder(
            application,
            PizzaDatabase::class.java, PizzaDatabase.DB_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideExampleRepository(database: PizzaDatabase): ExampleRepository {
        return ExampleRoomRepository(database.exampleDao())
    }

    @Provides
    @Singleton
    fun provideMenuRepository(database: PizzaDatabase): MenuRepository {
        return MenuRoomRepository(database.menuDao())
    }

    @Provides
    @Singleton
    fun provideOrderRepository(database: PizzaDatabase): OrderRepository {
        return OrderRoomRepository(
            database.menuDao(),
            database.orderDao(),
            database.orderDetailsDao()
        )
    }

    @Provides
    @Singleton
    fun provideOrderDetailsRepository(database: PizzaDatabase): OrderDetailsRepository {
        return OrderDetailsRoomRepository(database.orderDao(), database.orderDetailsDao())
    }

    @Provides
    @Singleton
    fun provideExampleViewModel(exampleUseCases: ExampleUseCases): ExampleViewModel {
        return ExampleViewModel(exampleUseCases)
    }

    @Provides
    @Singleton
    fun provideExampleUseCases(repository: ExampleRepository): ExampleUseCases {
        return ExampleUseCases(
            getExamples = GetExamplesUseCase(repository),
            addExample = AddExampleUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideFrontUseCases(
        menuRepository: MenuRepository,
        orderRepository: OrderRepository,
        orderDetailsRepository: OrderDetailsRepository
    ): FrontUseCases {
        return FrontUseCases(
            getPizzaList = GetPizzaListUseCase(menuRepository = menuRepository),
            getJuiceList = GetJuiceListUseCase(menuRepository = menuRepository),
            getOrderList = GetCurrentOrderUseCase(orderRepository = orderRepository),
            orderFood = OrderFoodUseCase(orderRepository = orderRepository),
            updateOrder = UpdateOrderUseCase(orderDetailsRepository = orderDetailsRepository),
            checkout = CheckoutUseCase(orderRepository = orderRepository)
        )
    }

    @Provides
    @Singleton
    fun provideAdminUseCases(
        menuRepository: MenuRepository,
        orderRepository: OrderRepository
    ): AdminUseCases {
        return AdminUseCases(
            insertMenu = InsertMenuUseCase(menuRepository = menuRepository),
            updateMenu = UpdateMenuUseCase(menuRepository = menuRepository),
            deleteMenu = DeleteMenuUseCase(menuRepository = menuRepository),
            getMenuList = GetMenuListUseCase(menuRepository = menuRepository),
            getOrderList = GetOrderListUseCase(orderRepository = orderRepository)
        )
    }
}