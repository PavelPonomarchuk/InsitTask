package ru.ponomarchukpn.insittask.data.mapper

import ru.ponomarchukpn.insittask.data.database.TodoDbModel
import ru.ponomarchukpn.insittask.data.network.TodoDto
import ru.ponomarchukpn.insittask.domain.entity.TodoEntity
import ru.ponomarchukpn.insittask.domain.entity.TodoStatus
import javax.inject.Inject

class TodoMapper @Inject constructor() {

    fun mapDbModelListToEntities(dbModelList: List<TodoDbModel>) = dbModelList.map {
        mapDbModelToEntity(it)
    }

    private fun mapDbModelToEntity(dbModel: TodoDbModel) = TodoEntity(
        description = dbModel.description,
        status = if (dbModel.status == TODO_STATUS_OPEN) {
            TodoStatus.OPEN
        } else {
            TodoStatus.DONE
        }
    )

    fun mapDtoListToDbModels(dtoList: List<TodoDto>) = dtoList.map {
        mapDtoToDbModel(it)
    }

    private fun mapDtoToDbModel(dto: TodoDto) = TodoDbModel(
        description = dto.description,
        status = dto.status
    )

    fun mapDbModelToDto(dbModel: TodoDbModel) = TodoDto(
        description = dbModel.description,
        status = dbModel.status
    )

    fun mapEntityToDto(todoEntity: TodoEntity) = TodoDto(
        description = todoEntity.description,
        status = if (todoEntity.status == TodoStatus.OPEN) {
            TODO_STATUS_OPEN
        } else {
            TODO_STATUS_DONE
        }
    )

    companion object {

        private const val TODO_STATUS_OPEN = "open"
        private const val TODO_STATUS_DONE = "done"
    }
}