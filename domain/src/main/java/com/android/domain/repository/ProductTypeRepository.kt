package com.android.domain.repository

import com.android.domain.model.ProductType

interface ProductTypeRepository {
    suspend fun getProductTypeList(force: Boolean): Result<List<ProductType>>
    suspend fun insertProductTypeList(productTypeList: List<ProductType>): Result<Unit>
}