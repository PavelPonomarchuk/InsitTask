package ru.ponomarchukpn.insittask.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.ponomarchukpn.insittask.data.database.AppDatabase
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context) = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        DB_NAME
    ).build()

    @Provides
    @Singleton
    fun provideTodoDao(database: AppDatabase) = database.todoDao()

    companion object {

        private const val DB_NAME = "insit_test.db"
    }
}