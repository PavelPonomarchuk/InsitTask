package ru.ponomarchukpn.insittask.domain.usecases

import ru.ponomarchukpn.insittask.domain.entity.TodoEntity
import ru.ponomarchukpn.insittask.domain.repository.TodoRepository
import javax.inject.Inject

class CreateTodoUseCase @Inject constructor(
    private val repository: TodoRepository
) {

    suspend fun createItem(todo: TodoEntity) = repository.createItem(todo)
}