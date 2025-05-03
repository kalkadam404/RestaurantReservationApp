package com.example.restaurantreservation.view.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.domain.model.Product
import com.android.domain.usecase.GetProductDetail
import com.android.domain.usecase.InsertProductDetail
import kotlinx.coroutines.launch

class ProductDetailsViewModel(
    private val getProductDetail: GetProductDetail,
    private val insertProductDetail: InsertProductDetail
) : ViewModel() {

    private val _productDetailUI = MutableLiveData<ProductDetailUI>()
    val productDetailUI: LiveData<ProductDetailUI> = _productDetailUI

    fun fetchProductDetail(productId: Int, force: Boolean) {
        viewModelScope.launch {
            _productDetailUI.value = ProductDetailUI.Loading(true)

            val params = GetProductDetail.Params(productId, force)

            getProductDetail.run(params).fold(
                onSuccess = { productDetail ->
                    saveLocal(productDetail)
                    _productDetailUI.value = ProductDetailUI.Success(productDetail)
                },
                onFailure = {
                    getProductDetail.run(GetProductDetail.Params(productId, false)).fold(
                        onSuccess = { localProductDetail ->
                            _productDetailUI.value = ProductDetailUI.Success(localProductDetail)
                        },
                        onFailure = { dbError ->
                            _productDetailUI.value = ProductDetailUI.Error(dbError.message)
                        }
                    )
                }
            )

            _productDetailUI.value = ProductDetailUI.Loading(false)
        }
    }

    private fun saveLocal(product: Product) {
        viewModelScope.launch {
            // Pass the product inside Params
            val params = InsertProductDetail.Params(product)
            insertProductDetail.run(params).fold(
                onSuccess = {
                    println("SaveLocalSuccess: Product saved")
                },
                onFailure = {
                    println("SaveLocalFailed: $it")
                }
            )
        }
    }
}

sealed interface ProductDetailUI {
    data class Loading(val isLoading: Boolean) : ProductDetailUI
    data class Error(val message: String? = null) : ProductDetailUI
    data class Success(val productDetail: Product) : ProductDetailUI
    data object Empty : ProductDetailUI
}
