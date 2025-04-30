package com.android.data.source.remote.api

import com.android.data.model.AuthRequest
import com.android.data.model.AuthResponse
import com.android.data.model.RegisterRequest
import java.io.IOException

class AuthRemoteDataSource(private val api: AuthApi) {

    suspend fun login(phoneNumber: String, password: String): Result<AuthResponse> {
        return try {
            val request = AuthRequest(phone_number = phoneNumber, password = password)
            val response = api.login(request)

            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(IOException("Ошибка авторизации: ${response.code()} ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun register(request: RegisterRequest): Result<Boolean> {
        return try {
            val response = api.register(request)

            if (response.isSuccessful) {
                Result.success(true)
            } else {
                Result.failure(IOException("Ошибка регистрации: ${response.code()} ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}