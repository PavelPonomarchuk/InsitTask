package ru.ponomarchukpn.insittask.di

import dagger.Binds
import dagger.Module
import ru.ponomarchukpn.insittask.data.repository.LoginRepositoryImpl
import ru.ponomarchukpn.insittask.data.repository.TodoRepositoryImpl
import ru.ponomarchukpn.insittask.domain.repository.LoginRepository
import ru.ponomarchukpn.insittask.domain.repository.TodoRepository
import javax.inject.Singleton

@Module
interface DomainModule {

    @Binds
    @Singleton
    fun bindCharactersRepository(impl: TodoRepositoryImpl): TodoRepository

    @Binds
    @Singleton
    fun bindLoginRepository(impl: LoginRepositoryImpl): LoginRepository
}