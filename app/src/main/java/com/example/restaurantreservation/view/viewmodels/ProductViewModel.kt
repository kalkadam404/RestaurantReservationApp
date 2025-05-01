package com.example.restaurantreservation.view.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.domain.model.Product
import com.android.domain.usecase.GetProductList
import com.android.domain.usecase.InsertProductList
import kotlinx.coroutines.launch

class ProductViewModel(
    private val getProductList: GetProductList,
    private val insertProductList: InsertProductList
) : ViewModel() {

    private val _productListUI = MutableLiveData<ProductListUI>()
    val productListUI: LiveData<ProductListUI> = _productListUI

    fun fetchRestaurantList() {
        viewModelScope.launch {
            _productListUI.value = ProductListUI.Loading(true)

            getProductList.run(true).fold(
                onSuccess = { productList ->
                    if (productList.isEmpty()) {
                        _productListUI.value = ProductListUI.Empty
                    } else {
                        saveLocal(productList)
                        _productListUI.value = ProductListUI.Success(productList)
                    }
                },
                onFailure = {
                    // If remote fetch fails, try local database
                    getProductList.run(false).fold(
                        onSuccess = { localProductList ->
                            if (localProductList.isEmpty()) {
                                _productListUI.value = ProductListUI.Empty
                            } else {
                                _productListUI.value = ProductListUI.Success(localProductList)
                            }
                        },
                        onFailure = { dbError ->
                            _productListUI.value = ProductListUI.Error(dbError.message)
                        }
                    )
                }
            )

            _productListUI.value = ProductListUI.Loading(false)
        }
    }

    private fun saveLocal(productList: List<Product>) {
        viewModelScope.launch {
            insertProductList.run(productList).fold(
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

sealed interface ProductListUI {
    data class Loading(val isLoading: Boolean) : ProductListUI
    data class Error(val message: String? = null) : ProductListUI
    data class Success(val movieList: List<Product>) : ProductListUI
    data object Empty : ProductListUI
}
