package com.android.domain.usecase

import com.android.domain.repository.AuthRepository

class IsUserLoggedInUseCase(private val repository: AuthRepository) {
    fun run(): Boolean = repository.isLoggedIn()
}