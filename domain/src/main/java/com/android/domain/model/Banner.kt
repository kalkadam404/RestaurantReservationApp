package com.android.domain.model

data class Banner(
    val id: Int,
    val name: String?,
    val subTitle: String?, // nullable
    val description: String?,
    val imageUrl: String?,
    val position: String?,
    val color: String?,
)
