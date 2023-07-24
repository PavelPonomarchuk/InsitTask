package ru.ponomarchukpn.insittask.data.network

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CookieInterceptor @Inject constructor(
    private val repository: CookieRepository
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request()
                .newBuilder()
                .addHeader("Cookie", repository.getCookie())
                .build()
        )
    }
}