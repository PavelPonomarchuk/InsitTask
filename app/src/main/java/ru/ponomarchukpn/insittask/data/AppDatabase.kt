package ru.ponomarchukpn.insittask.data

import androidx.room.Database
import androidx.room.RoomDatabase

//TODO add entities and dao

@Database(
    entities = [TodoDbModel::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

}