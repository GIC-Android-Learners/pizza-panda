package com.example.pizzapanda.presentation.example

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pizzapanda.domain.usecase.ExampleUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExampleViewModel @Inject constructor(private var exampleUseCases: ExampleUseCases) :
    ViewModel() {
    private val _exampleState: MutableState<ExampleState> = mutableStateOf(ExampleState(listOf()))
    val exampleState: State<ExampleState> = _exampleState

    init {
        viewModelScope.launch {
            getAllExamples()
        }
    }

    fun onEvent(event: ExampleEvent) {
        viewModelScope.launch {
            when (event) {
                is ExampleEvent.Add -> {
                    exampleUseCases.addExample(event.name)
                    getAllExamples()
                }
            }
        }
    }

    private suspend fun getAllExamples() {
        _exampleState.value = exampleState.value.copy(
            exampleList = exampleUseCases.getExamples()
        )
    }
}