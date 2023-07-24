package ru.ponomarchukpn.insittask.data.repository

import retrofit2.Response
import ru.ponomarchukpn.insittask.data.mapper.LoginMapper
import ru.ponomarchukpn.insittask.data.network.CookieRepository
import ru.ponomarchukpn.insittask.data.network.LoginApiService
import ru.ponomarchukpn.insittask.domain.entity.LoginStatus
import ru.ponomarchukpn.insittask.domain.entity.UserEntity
import ru.ponomarchukpn.insittask.domain.repository.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val apiService: LoginApiService,
    private val mapper: LoginMapper,
    private val cookieRepository: CookieRepository
) : LoginRepository {

    override suspend fun login(user: UserEntity): Boolean {
        val response = apiService.login(mapper.mapUserEntityToDto(user))

        return if (response.isSuccessful) {
            saveCookie(response)
        } else {
            false
        }
    }

    private fun saveCookie(response: Response<Unit>): Boolean {
        val cookie = response.headers().get("Set-Cookie")?.split(";")?.get(0)
        cookie?.let {
            cookieRepository.saveCookie(it)
            return true
        }
        return false
    }

    override fun checkLoginStatus() = if (cookieRepository.getCookie().isNotBlank()) {
        LoginStatus.AUTHORIZED
    } else {
        LoginStatus.UNAUTHORIZED
    }
}