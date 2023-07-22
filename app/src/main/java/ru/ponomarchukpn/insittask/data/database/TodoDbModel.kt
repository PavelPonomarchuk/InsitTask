package ru.ponomarchukpn.insittask.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = TodoDao.TABLE_NAME)
data class TodoDbModel(
    @PrimaryKey
    val description: String,
    val status: String
)