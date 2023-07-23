package ru.ponomarchukpn.insittask.domain.usecases

import ru.ponomarchukpn.insittask.domain.entity.UserEntity
import ru.ponomarchukpn.insittask.domain.repository.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: LoginRepository
) {

    suspend fun login(user: UserEntity) = repository.login(user)
}