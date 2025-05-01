package com.android.data.model

data class RegisterRequest(
    val phone_number: String,
    val name: String,
    val last_name: String,
    val email: String = "",
    val password: String,
    val password_confirm: String,
    val city: Int? = null,
    val language: String = "ru"
)