package com.example.pizzapanda.domain.repository

import com.example.pizzapanda.domain.model.Example

interface ExampleRepository {
    suspend fun getExamples(): List<Example>

    suspend fun addExample(example: Example)
}