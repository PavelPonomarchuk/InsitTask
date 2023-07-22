package ru.ponomarchukpn.insittask.domain.entity

data class TodoEntity(
    val description: String,
    val status: TodoStatus
)
