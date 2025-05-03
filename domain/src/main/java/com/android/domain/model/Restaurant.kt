package com.android.domain.model

import java.io.Serializable

data class Restaurant(
    val id: Int,
    val imageRes: String?,
    val name: String,
    val restaurantPlace: String,
    val opening: String?,
    val closing: String?,
): Serializable

