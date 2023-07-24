package ru.ponomarchukpn.insittask.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import ru.ponomarchukpn.insittask.domain.entity.LoginStatus
import ru.ponomarchukpn.insittask.domain.entity.TodoEntity
import ru.ponomarchukpn.insittask.domain.entity.TodoStatus
import ru.ponomarchukpn.insittask.domain.usecases.CheckLoginStatusUseCase
import ru.ponomarchukpn.insittask.domain.usecases.GetTodosUseCase
import ru.ponomarchukpn.insittask.domain.usecases.LoadTodosUseCase
import ru.ponomarchukpn.insittask.domain.usecases.UpdateTodoStatusUseCase
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val checkLoginStatusUseCase: CheckLoginStatusUseCase,
    private val getTodosUseCase: GetTodosUseCase,
    private val loadTodosUseCase: LoadTodosUseCase,
    private val updateTodoStatusUseCase: UpdateTodoStatusUseCase
) : ViewModel() {

    private val _loginStatus = MutableStateFlow<LoginStatus?>(null)
    val loginStatus = _loginStatus.asStateFlow()
        .filterNotNull()

    private val _todoList = MutableStateFlow<List<TodoEntity>?>(null)
    val todoList = _todoList.asStateFlow()
        .filterNotNull()

    fun onStartWorking() {
        val status = checkLoginStatusUseCase.checkLoginStatus()
        _loginStatus.tryEmit(status)
    }

    fun onUserAuthorized() {
        getTodoList()
        loadTodoList()
    }

    fun onItemLongClicked(item: TodoEntity) {
        val newItem = TodoEntity(
            description = item.description,
            status = if (item.status == TodoStatus.OPEN) {
                TodoStatus.DONE
            } else {
                TodoStatus.OPEN
            }
        )
        viewModelScope.launch(Dispatchers.IO) {
            val updated = updateTodoStatusUseCase.updateItemStatus(newItem)
            if (updated) {
                loadTodoList()
            }
        }
    }

    private fun getTodoList() {
        viewModelScope.launch(Dispatchers.IO) {
            getTodosUseCase.getItems().collect {
                _todoList.tryEmit(it)
            }
        }
    }

    private fun loadTodoList() {
        viewModelScope.launch(Dispatchers.IO) {
            loadTodosUseCase.loadItems()
        }
    }
}