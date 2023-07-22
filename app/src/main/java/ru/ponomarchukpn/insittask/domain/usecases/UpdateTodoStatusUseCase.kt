package ru.ponomarchukpn.insittask.domain.usecases

import ru.ponomarchukpn.insittask.domain.entity.TodoEntity
import ru.ponomarchukpn.insittask.domain.repository.TodoRepository
import javax.inject.Inject

class UpdateTodoStatusUseCase @Inject constructor(
    private val repository: TodoRepository
) {

    suspend fun updateItemStatus(todo: TodoEntity) = repository.updateItemStatus(todo)
}