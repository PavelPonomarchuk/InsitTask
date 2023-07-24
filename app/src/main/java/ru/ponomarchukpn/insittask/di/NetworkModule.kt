package ru.ponomarchukpn.insittask.di

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.ponomarchukpn.insittask.data.network.CookieInterceptor
import ru.ponomarchukpn.insittask.data.network.LoginApiService
import ru.ponomarchukpn.insittask.data.network.TodoApiService
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideLoginApiService(): LoginApiService = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
        .create(LoginApiService::class.java)

    @Provides
    @Singleton
    fun provideTodoApiService(cookieInterceptor: CookieInterceptor): TodoApiService {
        val clientWithInterceptor = OkHttpClient.Builder()
            .addInterceptor(cookieInterceptor)
            .build()

        return Retrofit.Builder()
            .client(clientWithInterceptor)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(TodoApiService::class.java)
    }

    companion object {

        //for develop
//        private const val BASE_URL = "http://192.168.0.109:1080"
        private const val BASE_URL = "http://127.0.0.1:1080"
    }
}