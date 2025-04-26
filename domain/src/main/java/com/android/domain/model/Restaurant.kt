package com.android.domain.model

data class Restaurant(
    val id: String,
    val imageRes: String?,
    val name: String,
    val restaurantPlace: String,
    val opening: String?,
    val closing: String?,
)

