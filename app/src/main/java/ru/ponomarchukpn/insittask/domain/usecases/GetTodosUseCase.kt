package ru.ponomarchukpn.insittask.domain.usecases

import ru.ponomarchukpn.insittask.domain.repository.TodoRepository
import javax.inject.Inject

class GetTodosUseCase @Inject constructor(
    private val repository: TodoRepository
) {

    fun getItems() = repository.getItems()
}