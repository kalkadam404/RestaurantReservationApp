package com.android.data.repository

import com.android.data.model.RegisterRequest
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
            val response = result.getOrNull()
            response?.let {
                tokenManager.saveTokens(it.access, it.refresh)
                Result.success(true)
            } ?: Result.failure(Exception("Получен пустой ответ от сервера"))
        } else {
            Result.failure(result.exceptionOrNull() ?: Exception("Ошибка авторизации"))
        }
    }

    override suspend fun register(userData: Map<String, Any>): Result<Boolean> {
        val request = RegisterRequest(
            phone_number = userData["phone_number"]?.toString() ?: "",
            name = userData["name"]?.toString() ?: "",
            last_name = userData["last_name"]?.toString() ?: "",
            email = userData["email"]?.toString() ?: "",
            password = userData["password"]?.toString() ?: "",
            password_confirm = userData["password_confirm"]?.toString() ?: "",
            city = userData["city"]?.toString()?.toIntOrNull(),
            language = userData["language"]?.toString() ?: "ru",
        )

        return remoteDataSource.register(request)
    }

    override fun isLoggedIn(): Boolean = tokenManager.isLoggedIn()

    override fun logout() = tokenManager.clearTokens()

    override fun getAccessToken(): String? = tokenManager.getAccessToken()
}