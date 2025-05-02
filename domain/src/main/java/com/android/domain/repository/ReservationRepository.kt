package com.android.domain.repository

import com.android.domain.model.Reservation

interface ReservationRepository {
    suspend fun getReservationList(force: Boolean): Result<List<Reservation>>
    suspend fun insertReservationList(reservationList: List<Reservation>): Result<Unit>
}