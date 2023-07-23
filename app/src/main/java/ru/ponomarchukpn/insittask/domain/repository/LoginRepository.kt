package ru.ponomarchukpn.insittask.domain.repository

import ru.ponomarchukpn.insittask.domain.entity.LoginStatus
import ru.ponomarchukpn.insittask.domain.entity.UserEntity

interface LoginRepository {

    suspend fun login(user: UserEntity): Boolean

    fun checkLoginStatus(): LoginStatus
}