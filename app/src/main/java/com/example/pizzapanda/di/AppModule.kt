package com.example.pizzapanda.di

import android.app.Application
import androidx.room.Room
import com.example.pizzapanda.data.PizzaDatabase
import com.example.pizzapanda.data.example.ExampleRoomRepository
import com.example.pizzapanda.domain.repository.ExampleRepository
import com.example.pizzapanda.domain.usecase.AddExampleUseCase
import com.example.pizzapanda.domain.usecase.ExampleUseCases
import com.example.pizzapanda.domain.usecase.GetExamplesUseCase
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
}