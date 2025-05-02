package com.android.data.model.product

data class ProductItemsResponse (
    val id: Int,
    val menu_item_details: ProductResponse,
    val quantity: Int,
)