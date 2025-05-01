package com.example.restaurantreservation.view.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.domain.model.Banner
import com.android.domain.usecase.GetBannerList
import com.android.domain.usecase.InsertBannerList
import kotlinx.coroutines.launch

class BannerViewModel(
    private val getBannerList: GetBannerList,
    private val insertBannerList: InsertBannerList
) : ViewModel() {

    private val _bannerListUI = MutableLiveData<BannerListUI>()
    val bannerListUI: LiveData<BannerListUI> = _bannerListUI

    fun fetchBannerList() {
        viewModelScope.launch {
            _bannerListUI.value = BannerListUI.Loading(true)

            getBannerList.run(true).fold(
                onSuccess = { bannerList ->
                    if (bannerList.isEmpty()) {
                        _bannerListUI.value = BannerListUI.Empty
                    } else {
                        saveLocal(bannerList)
                        _bannerListUI.value = BannerListUI.Success(bannerList)
                    }
                },
                onFailure = {
                    // If remote fetch fails, try local database
                    getBannerList.run(false).fold(
                        onSuccess = { localBannerList ->
                            if (localBannerList.isEmpty()) {
                                _bannerListUI.value = BannerListUI.Empty
                            } else {
                                _bannerListUI.value = BannerListUI.Success(localBannerList)
                            }
                        },
                        onFailure = { dbError ->
                            _bannerListUI.value = BannerListUI.Error(dbError.message)
                        }
                    )
                }
            )

            _bannerListUI.value = BannerListUI.Loading(false)
        }
    }

    private fun saveLocal(bannerList: List<Banner>) {
        viewModelScope.launch {
            insertBannerList.run(bannerList).fold(
                onSuccess = {
                    println("SaveLocalSuccess3: $it")
                },
                onFailure = {
                    println("SaveLocalFailed3: $it")
                }
            )
        }
    }
}

sealed interface BannerListUI {
    data class Loading(val isLoading: Boolean) : BannerListUI
    data class Error(val message: String? = null) : BannerListUI
    data class Success(val bannerList: List<Banner>) : BannerListUI
    data object Empty : BannerListUI
}
