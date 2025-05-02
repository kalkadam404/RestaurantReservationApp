package com.android.domain.usecase

import com.android.domain.model.Reservation
import com.android.domain.repository.ReservationRepository
import com.android.domain.util.UseCase

class GetReservationList (
    private val repository: ReservationRepository
) : UseCase<List<Reservation>, Boolean>() {

    override suspend fun run(params: Boolean): Result<List<Reservation>> {
        return repository.getReservationList(params)
    }
}