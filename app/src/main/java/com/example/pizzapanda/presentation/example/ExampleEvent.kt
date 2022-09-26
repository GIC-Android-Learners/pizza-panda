package com.example.pizzapanda.presentation.example

sealed class ExampleEvent {
    data class Add(val name: String) : ExampleEvent()
}