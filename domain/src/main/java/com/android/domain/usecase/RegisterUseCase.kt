package com.android.domain.usecase

import com.android.domain.repository.AuthRepository

class RegisterUseCase(private val repository: AuthRepository) {
    suspend fun run(userData: Map<String, Any>): Result<Boolean> {
        return repository.register(userData)
    }
}