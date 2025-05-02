package com.android.data.model.reservation

import com.android.data.model.product.ProductItemsResponse
import com.android.data.model.restaurant.RestaurantDetailResponse2

data class ReservationResponse(
    val id: Int,
    val restaurant_details: RestaurantDetailResponse2,
    val table_details: TableResponse,
    val reservation_date: String?,
    val start_time: String?,
    val end_time: String?,
    val guest_count: Int,
    val guest_name: String?,
    val guest_phone: String?,
    val guest_email: String?,
    val status: String?,
    val status_display: String?,
    val special_requests: String?,
    val created_at: String?,
    val updated_at: String?,
    val menu_items: List<ProductItemsResponse>
)
