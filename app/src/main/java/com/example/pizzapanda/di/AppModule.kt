package com.example.pizzapanda.di

import android.app.Application
import androidx.room.Room
import com.example.pizzapanda.data.PizzaDatabase
import com.example.pizzapanda.data.menu.MenuRoomRepository
import com.example.pizzapanda.data.order.OrderRoomRepository
import com.example.pizzapanda.data.orderDetails.OrderDetailsRoomRepository
import com.example.pizzapanda.domain.repository.MenuRepository
import com.example.pizzapanda.domain.repository.OrderDetailsRepository
import com.example.pizzapanda.domain.repository.OrderRepository
import com.example.pizzapanda.domain.storage.FileStorage
import com.example.pizzapanda.storage.LocalFileStorage
import com.example.pizzapanda.domain.usecase.admin.*
import com.example.pizzapanda.domain.usecase.front.*
import com.example.pizzapanda.domain.usecase.front.GetCurrentOrderUseCase
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
        return OrderDetailsRoomRepository(database.orderDetailsDao())
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
        orderRepository: OrderRepository,
        fileStorage: FileStorage,
        application: Application
    ): AdminUseCases {
        return AdminUseCases(
            insertMenu = InsertMenuUseCase(
                menuRepository = menuRepository,
                fileStorage = fileStorage,
                application = application
            ),
            updateMenu = UpdateMenuUseCase(
                menuRepository = menuRepository,
                fileStorage = fileStorage,
                application = application
            ),
            deleteMenu = DeleteMenuUseCase(
                menuRepository = menuRepository,
                fileStorage = fileStorage
            ),
            getMenuList = GetMenuListUseCase(menuRepository = menuRepository),
            getOrderList = GetOrderListUseCase(orderRepository = orderRepository),
            getMenuListByCategory = GetMenuListByCategoryUseCase(menuRepository = menuRepository)
        )
    }

    @Provides
    @Singleton
    fun provideFileStorage(application: Application): FileStorage {
        return LocalFileStorage(application)
    }
}