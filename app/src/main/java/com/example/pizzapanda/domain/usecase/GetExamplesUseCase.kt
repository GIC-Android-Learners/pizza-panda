package com.example.pizzapanda.domain.usecase

import com.example.pizzapanda.domain.model.Example
import com.example.pizzapanda.domain.repository.ExampleRepository

class GetExamplesUseCase(private val exampleRepository: ExampleRepository) {
    suspend operator fun invoke(): List<Example> {
        return exampleRepository.getExamples()
    }
}