package com.android.domain.model

sealed class MenuItem {
    data class SectionHeader(val title: String) : MenuItem()
    data class DishItem(
        val name: String,
        val price: String,
        val imageResId: Int
    ) : MenuItem()
}
