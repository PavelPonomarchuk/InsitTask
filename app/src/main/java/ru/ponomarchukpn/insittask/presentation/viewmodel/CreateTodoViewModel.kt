package ru.ponomarchukpn.insittask.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import ru.ponomarchukpn.insittask.domain.entity.CreateTodoStatus
import ru.ponomarchukpn.insittask.domain.entity.TodoEntity
import ru.ponomarchukpn.insittask.domain.entity.TodoStatus
import ru.ponomarchukpn.insittask.domain.usecases.CreateTodoUseCase
import javax.inject.Inject

class CreateTodoViewModel @Inject constructor(
    private val createTodoUseCase: CreateTodoUseCase
) : ViewModel() {

    private val _createTodoState = MutableSharedFlow<CreateTodoStatus>(extraBufferCapacity = 1)
    val createTodoState = _createTodoState.asSharedFlow()

    fun onButtonSavePressed(description: String) {
        val todo = TodoEntity(description, TodoStatus.OPEN)
        viewModelScope.launch(Dispatchers.IO) {
            val result = createTodoUseCase.createItem(todo)
            _createTodoState.tryEmit(result)
        }
    }
}