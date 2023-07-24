package ru.ponomarchukpn.insittask.data.network

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CookieRepository @Inject constructor(
    context: Context
) {

    private val preferences: SharedPreferences = context.getSharedPreferences(
        COOKIE_PREFERENCES_NAME, Context.MODE_PRIVATE
    )

    private var cookie: String? = null

    fun saveCookie(newCookie: String) {
        preferences.edit().putString(KEY_COOKIE, newCookie).apply()
        cookie = newCookie
    }

    fun getCookie(): String {
        if (cookie == null) {
            cookie = preferences.getString(KEY_COOKIE, UNDEFINED_COOKIE)
        }

        return cookie!!
    }

    companion object {

        private const val UNDEFINED_COOKIE = ""
        private const val COOKIE_PREFERENCES_NAME = "cookieCustomPreferences"
        private const val KEY_COOKIE = "keyCookie"
    }
}