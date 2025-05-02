package com.android.data.source.local

import com.android.data.model.reservation.ReservationEntity
import com.android.data.source.local.dao.ReservationDao

class ReservationLocalDataSource (private val dao: ReservationDao){

    suspend fun fetchReservationList() = dao.getAll()

    suspend fun insertReservationList(reservationList: List<ReservationEntity>) = dao.insertAll(reservationList)
}