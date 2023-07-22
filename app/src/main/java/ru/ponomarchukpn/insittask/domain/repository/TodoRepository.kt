package ru.ponomarchukpn.insittask.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.ponomarchukpn.insittask.domain.entity.TodoEntity

interface TodoRepository {

    fun getItems(): Flow<List<TodoEntity>>

    suspend fun updateItemStatus(todo: TodoEntity): Boolean

    suspend fun createItem(todo: TodoEntity): Boolean
}