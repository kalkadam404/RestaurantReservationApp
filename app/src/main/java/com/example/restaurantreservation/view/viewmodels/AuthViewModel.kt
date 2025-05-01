package com.example.restaurantreservation.view.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.data.model.RegisterRequest
import com.android.domain.usecase.LoginUseCase
import com.android.domain.usecase.RegisterUseCase
import kotlinx.coroutines.launch
import java.lang.Exception

class AuthViewModel(
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    private val _loginResult = MutableLiveData<AuthUI>()
    val loginResult: LiveData<AuthUI> = _loginResult

    private val _registerResult = MutableLiveData<AuthUI>()
    val registerResult: LiveData<AuthUI> = _registerResult

    fun login(phoneNumber: String, password: String) {
        viewModelScope.launch {
            _loginResult.value = AuthUI.Loading(true)

            loginUseCase.run(phoneNumber, password).fold(
                onSuccess = {
                    _loginResult.value = AuthUI.Success
                },
                onFailure = { error ->
                    Log.e("AuthViewModel", "Login error: ${error.message}", error)
                    _loginResult.value = AuthUI.Error(error.message)
                }
            )

            _loginResult.value = AuthUI.Loading(false)
        }
    }

    fun register(name: String, lastName: String, phoneNumber: String, email: String, password: String, passwordConfirm: String) {
        viewModelScope.launch {
            _registerResult.value = AuthUI.Loading(true)

            try {
                val userData = mapOf(
                    "name" to name,
                    "last_name" to lastName,
                    "phone_number" to phoneNumber,
                    "email" to email,
                    "password" to password,
                    "password_confirm" to passwordConfirm,
                    "language" to "ru"
                )

                registerUseCase.run(userData).fold(
                    onSuccess = {
                        _registerResult.value = AuthUI.Success
                    },
                    onFailure = { error ->
                        _registerResult.value = AuthUI.Error(error.message)
                    }
                )
            } catch (e: Exception) {
                _registerResult.value = AuthUI.Error(e.message)
            }

            _registerResult.value = AuthUI.Loading(false)
        }
    }
}

sealed interface AuthUI {
    data class Loading(val isLoading: Boolean) : AuthUI
    data class Error(val message: String?) : AuthUI
    data object Success : AuthUI
}