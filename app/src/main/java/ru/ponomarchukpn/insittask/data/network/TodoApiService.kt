package ru.ponomarchukpn.insittask.data.network

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface TodoApiService {

    @GET("task")
    suspend fun loadItems(): List<TodoDto>

    @POST("task")
    suspend fun create(
        @Body todoList: List<TodoDto>
    ): Response<Unit>

    @PUT("task")
    suspend fun updateItemStatus(
        @Body todo: TodoDto
    ): Response<Unit>
}