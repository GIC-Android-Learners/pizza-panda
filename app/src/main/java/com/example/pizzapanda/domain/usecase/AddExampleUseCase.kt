package com.example.pizzapanda.domain.usecase

import com.example.pizzapanda.domain.model.Example
import com.example.pizzapanda.domain.repository.ExampleRepository

class AddExampleUseCase(private val exampleRepository: ExampleRepository) {
    suspend operator fun invoke(name: String) {
        exampleRepository.addExample(Example(name = name))
    }
}