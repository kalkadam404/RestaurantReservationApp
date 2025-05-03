package com.example.restaurantreservation.view.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.domain.model.Restaurant
import com.android.domain.usecase.GetRestaurantDetail
import com.android.domain.usecase.InsertRestaurantDetail
import kotlinx.coroutines.launch

class RestaurantDetailsViewModel(
    private val getRestaurantDetail: GetRestaurantDetail,
    private val insertRestaurantDetail: InsertRestaurantDetail
) : ViewModel() {

    private val _restaurantDetailUI = MutableLiveData<RestaurantDetailUI>()
    val restaurantDetailUI: LiveData<RestaurantDetailUI> = _restaurantDetailUI

    fun fetchRestaurantDetail(restaurantId: Int, force: Boolean) {
        viewModelScope.launch {
            _restaurantDetailUI.value = RestaurantDetailUI.Loading(true)

            val params = GetRestaurantDetail.Params(restaurantId, force)

            getRestaurantDetail.run(params).fold(
                onSuccess = { restaurantDetail ->
                    saveLocal(restaurantDetail)
                    _restaurantDetailUI.value = RestaurantDetailUI.Success(restaurantDetail)
                },
                onFailure = {
                    // If remote fetch fails, try local database
                    getRestaurantDetail.run(GetRestaurantDetail.Params(restaurantId, false)).fold(
                        onSuccess = { localRestaurantDetail ->
                            _restaurantDetailUI.value = RestaurantDetailUI.Success(localRestaurantDetail)
                        },
                        onFailure = { dbError ->
                            _restaurantDetailUI.value = RestaurantDetailUI.Error(dbError.message)
                        }
                    )
                }
            )

            _restaurantDetailUI.value = RestaurantDetailUI.Loading(false)
        }
    }

    private fun saveLocal(restaurant: Restaurant) {
        viewModelScope.launch {
            // Wrap the restaurant object in Params
            val params = InsertRestaurantDetail.Params(restaurant)
            insertRestaurantDetail.run(params).fold(
                onSuccess = {
                    println("SaveLocalSuccess: Restaurant saved")
                },
                onFailure = {
                    println("SaveLocalFailed: $it")
                }
            )
        }
    }
}

sealed interface RestaurantDetailUI {
    data class Loading(val isLoading: Boolean) : RestaurantDetailUI
    data class Error(val message: String? = null) : RestaurantDetailUI
    data class Success(val restaurantDetail: Restaurant) : RestaurantDetailUI
    data object Empty : RestaurantDetailUI
}
