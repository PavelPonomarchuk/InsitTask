package ru.ponomarchukpn.insittask.domain.usecases

import ru.ponomarchukpn.insittask.domain.repository.TodoRepository
import javax.inject.Inject

class LoadTodosUseCase @Inject constructor(
    private val repository: TodoRepository
) {

    suspend fun loadItems() = repository.loadItems()
}