package com.example.restaurantreservation.view.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.domain.model.Reservation
import com.android.domain.usecase.GetReservationList
import com.android.domain.usecase.InsertReservationList
import kotlinx.coroutines.launch

class ReservationViewModel(
    private val getReservationList: GetReservationList,
    private val insertReservationList: InsertReservationList
) : ViewModel() {

    private val _reservationListUI = MutableLiveData<ReservationListUI>()
    val reservationListUI: LiveData<ReservationListUI> = _reservationListUI

    fun fetchReservationList() {
        viewModelScope.launch {
            _reservationListUI.value = ReservationListUI.Loading(true)

            getReservationList.run(true).fold(
                onSuccess = { reservationList ->
                    if (reservationList.isEmpty()) {
                        _reservationListUI.value = ReservationListUI.Empty
                    } else {
                        saveLocal(reservationList)
                        _reservationListUI.value = ReservationListUI.Success(reservationList)
                    }
                },
                onFailure = {
                    // If remote fetch fails, try local database
                    getReservationList.run(false).fold(
                        onSuccess = { localReservationList ->
                            if (localReservationList.isEmpty()) {
                                _reservationListUI.value = ReservationListUI.Empty
                            } else {
                                _reservationListUI.value = ReservationListUI.Success(localReservationList)
                            }
                        },
                        onFailure = { dbError ->
                            _reservationListUI.value = ReservationListUI.Error(dbError.message)
                        }
                    )
                }
            )

            _reservationListUI.value = ReservationListUI.Loading(false)
        }
    }

    private fun saveLocal(reservationList: List<Reservation>) {
        viewModelScope.launch {
            insertReservationList.run(reservationList).fold(
                onSuccess = {
                    println("SaveLocalSuccess2: $it")
                },
                onFailure = {
                    println("SaveLocalFailed2: $it")
                }
            )
        }
    }
}

sealed interface ReservationListUI {
    data class Loading(val isLoading: Boolean) : ReservationListUI
    data class Error(val message: String? = null) : ReservationListUI
    data class Success(val reservationList: List<Reservation>) : ReservationListUI
    data object Empty : ReservationListUI
}
