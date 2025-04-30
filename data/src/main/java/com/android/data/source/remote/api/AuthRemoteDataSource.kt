package com.android.data.source.remote.api

import com.android.data.model.AuthRequest
import com.android.data.model.AuthResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthRemoteDataSource(private val api: AuthApi) {

    suspend fun login(phoneNumber: String, password: String): Result<AuthResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val request = AuthRequest(phoneNumber, password)
                val response = api.login(request)

                if (response.isSuccessful) {
                    response.body()?.let {
                        return@withContext Result.success(it)
                    } ?: return@withContext Result.failure(Exception("Пустой ответ от сервера"))
                } else {
                    return@withContext Result.failure(Exception("Ошибка: ${response.code()} ${response.message()}"))
                }
            } catch (e: Exception) {
                return@withContext Result.failure(e)
            }
        }
    }

    suspend fun register(userData: Map<String, Any>): Result<Boolean> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.register(userData)

                if (response.isSuccessful) {
                    return@withContext Result.success(true)
                } else {
                    return@withContext Result.failure(Exception("Ошибка регистрации: ${response.code()} ${response.message()}"))
                }
            } catch (e: Exception) {
                return@withContext Result.failure(e)
            }
        }
    }
}