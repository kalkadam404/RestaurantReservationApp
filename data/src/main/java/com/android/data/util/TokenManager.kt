package com.android.data.util

import android.content.Context
import android.content.SharedPreferences

class TokenManager(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val ACCESS_TOKEN = "access_token"
        private const val REFRESH_TOKEN = "refresh_token"
        private const val IS_LOGGED_IN = "is_logged_in"
    }

    fun saveTokens(accessToken: String, refreshToken: String) {
        sharedPreferences.edit().apply {
            putString(ACCESS_TOKEN, accessToken)
            putString(REFRESH_TOKEN, refreshToken)
            putBoolean(IS_LOGGED_IN, true)
            apply()
        }
    }

    fun getAccessToken(): String? = sharedPreferences.getString(ACCESS_TOKEN, null)

    fun getRefreshToken(): String? = sharedPreferences.getString(REFRESH_TOKEN, null)

    fun isLoggedIn(): Boolean = sharedPreferences.getBoolean(IS_LOGGED_IN, false)

    fun clearTokens() {
        sharedPreferences.edit().apply {
            remove(ACCESS_TOKEN)
            remove(REFRESH_TOKEN)
            putBoolean(IS_LOGGED_IN, false)
            apply()
        }
    }
}