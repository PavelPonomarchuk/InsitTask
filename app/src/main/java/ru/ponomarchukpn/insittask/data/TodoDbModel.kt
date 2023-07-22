package ru.ponomarchukpn.insittask.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TodoDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val description: String,
    val status: String
)
