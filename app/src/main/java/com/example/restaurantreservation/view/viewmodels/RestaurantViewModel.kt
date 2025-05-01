package com.example.restaurantreservation.view.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.domain.model.Restaurant
import com.android.domain.usecase.GetRestaurantList
import com.android.domain.usecase.InsertRestaurantList
import kotlinx.coroutines.launch

class RestaurantViewModel(
    private val getRestaurantList: GetRestaurantList,
    private val insertRestaurantList: InsertRestaurantList
) : ViewModel() {

    private val _restaurantListUI = MutableLiveData<RestaurantListUI>()
    val restaurantListUI: LiveData<RestaurantListUI> = _restaurantListUI

    fun fetchRestaurantList() {
        viewModelScope.launch {
            _restaurantListUI.value = RestaurantListUI.Loading(true)

            getRestaurantList.run(true).fold(
                onSuccess = { restaurantList ->
                    if (restaurantList.isEmpty()) {
                        _restaurantListUI.value = RestaurantListUI.Empty
                    } else {
                        saveLocal(restaurantList)
                        _restaurantListUI.value = RestaurantListUI.Success(restaurantList)
                    }
                },
                onFailure = {
                    // If remote fetch fails, try local database
                    getRestaurantList.run(false).fold(
                        onSuccess = { localRestaurantList ->
                            if (localRestaurantList.isEmpty()) {
                                _restaurantListUI.value = RestaurantListUI.Empty
                            } else {
                                _restaurantListUI.value = RestaurantListUI.Success(localRestaurantList)
                            }
                        },
                        onFailure = { dbError ->
                            _restaurantListUI.value = RestaurantListUI.Error(dbError.message)
                        }
                    )
                }
            )

            _restaurantListUI.value = RestaurantListUI.Loading(false)
        }
    }

    private fun saveLocal(restaurantList: List<Restaurant>) {
        viewModelScope.launch {
            insertRestaurantList.run(restaurantList).fold(
                onSuccess = {
                    println("SaveLocalSuccess: $it")
                },
                onFailure = {
                    println("SaveLocalFailed: $it")
                }
            )
        }
    }
}

sealed interface RestaurantListUI {
    data class Loading(val isLoading: Boolean) : RestaurantListUI
    data class Error(val message: String? = null) : RestaurantListUI
    data class Success(val restaurantList: List<Restaurant>) : RestaurantListUI
    data object Empty : RestaurantListUI
}
