package com.android.domain.repository

interface AuthRepository {
    suspend fun login(phoneNumber: String, password: String): Result<Boolean>
    suspend fun register(userData: Map<String, Any>): Result<Boolean>
    fun isLoggedIn(): Boolean
    fun logout()
    fun getAccessToken(): String?
}