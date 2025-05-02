package com.example.restaurantreservation.view.viewmodels


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.domain.model.ProductType
import com.android.domain.usecase.GetProductTypeList
import com.android.domain.usecase.InsertProductTypeList
import kotlinx.coroutines.launch

class ProductTypeViewModel(
    private val getProductTypeList: GetProductTypeList,
    private val insertProductTypeList: InsertProductTypeList
) : ViewModel() {

    private val _productTypeListUI = MutableLiveData<ProductTypeListUI>()
    val productTypeListUI: LiveData<ProductTypeListUI> = _productTypeListUI

    fun fetchProductTypeList() {
        viewModelScope.launch {
            _productTypeListUI.value = ProductTypeListUI.Loading(true)

            getProductTypeList.run(true).fold(
                onSuccess = { productTypeList ->
                    if (productTypeList.isEmpty()) {
                        _productTypeListUI.value = ProductTypeListUI.Empty
                    } else {
                        saveLocal(productTypeList)
                        _productTypeListUI.value = ProductTypeListUI.Success(productTypeList)
                    }
                },
                onFailure = {
                    // If remote fetch fails, try local database
                    getProductTypeList.run(false).fold(
                        onSuccess = { localProductTypeList ->
                            if (localProductTypeList.isEmpty()) {
                                _productTypeListUI.value = ProductTypeListUI.Empty
                            } else {
                                _productTypeListUI.value = ProductTypeListUI.Success(localProductTypeList)
                            }
                        },
                        onFailure = { dbError ->
                            _productTypeListUI.value = ProductTypeListUI.Error(dbError.message)
                        }
                    )
                }
            )

            _productTypeListUI.value = ProductTypeListUI.Loading(false)
        }
    }

    private fun saveLocal(productTypeList: List<ProductType>) {
        viewModelScope.launch {
            insertProductTypeList.run(productTypeList).fold(
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

sealed interface ProductTypeListUI {
    data class Loading(val isLoading: Boolean) : ProductTypeListUI
    data class Error(val message: String? = null) : ProductTypeListUI
    data class Success(val productTypeList: List<ProductType>) : ProductTypeListUI
    data object Empty : ProductTypeListUI
}
