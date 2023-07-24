package ru.ponomarchukpn.insittask.data.network

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApiService {

    @POST("login")
    suspend fun login(
        @Body user: UserDto
    ): Response<Unit>
}