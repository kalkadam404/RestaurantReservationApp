package com.android.data.model.reservation

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Reservation")
data class ReservationEntity (
    @PrimaryKey val id: Int,
    val restaurantName: String?,
    val restaurantCityName: String?,
    val tableNumber: Int,
    val tableSection: String?,
    val tableQr: String?,
    val tableCallTime: String?,
    @ColumnInfo("image_url")val imageUrl: String?,
    val reservationDate: String?,
    val startTime: String?,
    val endTime: String?,
    val guestCount: Int,
    val guestName: String?,
    val guestPhone: String?,
    val guestEmail: String?,
    val status: String?,
    val statusDisplay: String?,
    val specialRequests: String?,
    val productName: String?,
)