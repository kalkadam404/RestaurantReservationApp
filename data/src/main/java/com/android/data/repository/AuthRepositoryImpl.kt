package com.android.data.repository

import com.android.data.model.AuthResponse
import com.android.data.source.remote.api.AuthRemoteDataSource
import com.android.data.util.TokenManager
import com.android.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val remoteDataSource: AuthRemoteDataSource,
    private val tokenManager: TokenManager
) : AuthRepository {

    override suspend fun login(phoneNumber: String, password: String): Result<Boolean> {
        val result = remoteDataSource.login(phoneNumber, password)
        return if (result.isSuccess) {
            val response = result.getOrNull() as AuthResponse?
            response?.let {
                tokenManager.saveTokens(it.access, it.refresh)
                Result.success(true)
            } ?: Result.failure(Exception("Получен пустой ответ от сервера"))
        } else {
            Result.failure(result.exceptionOrNull() ?: Exception("Ошибка авторизации"))
        }
    }

    override suspend fun register(userData: Map<String, Any>): Result<Boolean> {
        return remoteDataSource.register(userData)
    }

    override fun isLoggedIn(): Boolean = tokenManager.isLoggedIn()

    override fun logout() = tokenManager.clearTokens()

    override fun getAccessToken(): String? = tokenManager.getAccessToken()
}