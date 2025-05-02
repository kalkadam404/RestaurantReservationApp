package com.android.domain.model

data class Reservation (
    val id: Int,
    val restaurantName: String?,
    val restaurantCityName: String?,
    val restaurant_photo: String?,
    val tableNumber: Int,
    val tableSection: String?,
    val tableQr: String?,
    val tableCallTime: String?,
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
    val productName: String?,
    )