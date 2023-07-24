package ru.ponomarchukpn.insittask.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Query("SELECT * FROM $TABLE_NAME ORDER BY description")
    fun getAll(): Flow<List<TodoDbModel>>

    @Query("SELECT * FROM $TABLE_NAME ORDER BY description")
    suspend fun getAllAsList(): List<TodoDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(list: List<TodoDbModel>)

    companion object {

        const val TABLE_NAME = "todos"
    }
}