package com.example.pizzapanda.data.example

import com.example.pizzapanda.domain.model.Example
import com.example.pizzapanda.domain.repository.ExampleRepository

class ExampleRoomRepository(private val exampleDao: ExampleDao) : ExampleRepository {
    override suspend fun getExamples(): List<Example> {
        return exampleDao.getAll().map {
            Example(
                id = it.id,
                name = it.name
            )
        }
    }

    override suspend fun addExample(example: Example) {
        exampleDao.insert(
            ExampleEntity(
                name = example.name
            )
        )
    }
}