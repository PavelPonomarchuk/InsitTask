package ru.ponomarchukpn.insittask.domain.usecases

import ru.ponomarchukpn.insittask.domain.repository.LoginRepository
import javax.inject.Inject

class CheckLoginStatusUseCase @Inject constructor(
    private val repository: LoginRepository
) {

    fun checkLoginStatus() = repository.checkLoginStatus()
}