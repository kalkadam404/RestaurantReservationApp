package com.android.domain.usecase

import com.android.domain.repository.AuthRepository

class LoginUseCase(private val repository: AuthRepository) {
    suspend fun run(phoneNumber: String, password: String): Result<Boolean> {
        return repository.login(phoneNumber, password)
    }
}