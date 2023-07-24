package ru.ponomarchukpn.insittask.data.repository

import kotlinx.coroutines.flow.map
import ru.ponomarchukpn.insittask.data.database.TodoDao
import ru.ponomarchukpn.insittask.data.mapper.TodoMapper
import ru.ponomarchukpn.insittask.data.network.TodoApiService
import ru.ponomarchukpn.insittask.domain.entity.CreateTodoStatus
import ru.ponomarchukpn.insittask.domain.entity.TodoEntity
import ru.ponomarchukpn.insittask.domain.repository.TodoRepository
import javax.inject.Inject

class TodoRepositoryImpl @Inject constructor(
    private val todoDao: TodoDao,
    private val todoApiService: TodoApiService,
    private val mapper: TodoMapper
) : TodoRepository {

    override fun getItems() = todoDao.getAll().map {
        mapper.mapDbModelListToEntities(it)
    }

    override suspend fun loadItems() {
        try {
            val dtoList = todoApiService.loadItems()
            todoDao.insertList(
                mapper.mapDtoListToDbModels(dtoList)
            )
        } catch (_: Throwable) {

        }
    }

    override suspend fun updateItemStatus(todo: TodoEntity): Boolean {
        val newTodoDto = mapper.mapEntityToDto(todo)
        val todoDtoList = todoDao.getAllAsList().map {
            mapper.mapDbModelToDto(it)
        }
        val newList = todoDtoList.map { dto ->
            if (dto.description == newTodoDto.description) {
                newTodoDto
            } else {
                dto
            }
        }

        return try {
            val response = todoApiService.create(newList)
            response.isSuccessful
        } catch (exc: Throwable) {
            false
        }
    }

    override suspend fun createItem(todo: TodoEntity): CreateTodoStatus {
        val todoDtoList = todoDao.getAllAsList().map {
            mapper.mapDbModelToDto(it)
        }.toMutableList()
        val todoDto = mapper.mapEntityToDto(todo)
        val exists = todoDtoList.any {
            it.description == todoDto.description
        }
        if (exists) {
            return CreateTodoStatus.EXISTS
        }
        todoDtoList.add(todoDto)

        return try {
            val response = todoApiService.create(todoDtoList)
            if (response.isSuccessful) {
                CreateTodoStatus.SUCCESS
            } else if (response.code() == 403) {
                CreateTodoStatus.UNAUTHORIZED
            } else {
                CreateTodoStatus.UNDEFINED_FAIL
            }
        } catch (_: Throwable) {
            CreateTodoStatus.UNDEFINED_FAIL
        }
    }
}