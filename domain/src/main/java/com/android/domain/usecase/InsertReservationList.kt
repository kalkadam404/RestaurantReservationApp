package com.android.domain.usecase

import com.android.domain.model.Reservation
import com.android.domain.repository.ReservationRepository
import com.android.domain.util.UseCase

class InsertReservationList(
    private val repository: ReservationRepository
) : UseCase<Unit, List<Reservation>>() {

    override suspend fun run(params: List<Reservation>): Result<Unit> {
        return repository.insertReservationList(params)
    }
}