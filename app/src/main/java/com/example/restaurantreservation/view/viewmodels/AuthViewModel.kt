package com.example.restaurantreservation.view.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.domain.usecase.LoginUseCase
import com.android.domain.usecase.RegisterUseCase
import kotlinx.coroutines.launch

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
                onFailure = {
                    _loginResult.value = AuthUI.Error(it.message)
                }
            )

            _loginResult.value = AuthUI.Loading(false)
        }
    }

    fun register(userData: Map<String, Any>) {
        viewModelScope.launch {
            _registerResult.value = AuthUI.Loading(true)

            registerUseCase.run(userData).fold(
                onSuccess = {
                    _registerResult.value = AuthUI.Success
                },
                onFailure = {
                    _registerResult.value = AuthUI.Error(it.message)
                }
            )

            _registerResult.value = AuthUI.Loading(false)
        }
    }
}

sealed interface AuthUI {
    data class Loading(val isLoading: Boolean) : AuthUI
    data class Error(val message: String?) : AuthUI
    data object Success : AuthUI
}