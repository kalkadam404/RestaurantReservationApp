package com.android.data.repository


import com.android.data.mapper.ReservationResponseMapper
import com.android.data.mapper.localReservationResponseMapper
import com.android.data.mapper.reservationToReservationEntityMapper
import com.android.data.source.local.ReservationLocalDataSource
import com.android.data.source.remote.api.RestaurantRemoteDataSource
import com.android.data.util.repository.BaseRepository
import com.android.domain.model.Reservation
import com.android.domain.repository.ReservationRepository


class ReservationRepositoryImpl (
    private val remoteDataSource: RestaurantRemoteDataSource,
    private val localDataSource: ReservationLocalDataSource
) : BaseRepository(), ReservationRepository {
    override suspend fun getReservationList(force: Boolean): Result<List<Reservation>> = safeApiCall({
        if (force) {
            remoteDataSource.fetchReservationList().results.map(ReservationResponseMapper)
        } else {
            val localReservationList = localDataSource.fetchReservationList()

            if (localReservationList.isEmpty()) {
                println("FetchType: Remote4")
                remoteDataSource.fetchReservationList().results.map(ReservationResponseMapper)
            } else {
                println("FetchType: Local4")
                localReservationList.map(localReservationResponseMapper)
            }
        }
    })

    override suspend fun insertReservationList(reservationList: List<Reservation>): Result<Unit> = safeApiCall({
        localDataSource.insertReservationList(reservationList.map(reservationToReservationEntityMapper))
    })

}