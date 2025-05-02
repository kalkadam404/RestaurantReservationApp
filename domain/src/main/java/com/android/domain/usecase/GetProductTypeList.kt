package com.android.domain.usecase

import com.android.domain.model.ProductType
import com.android.domain.repository.ProductTypeRepository
import com.android.domain.util.UseCase

class GetProductTypeList (
    private val repository: ProductTypeRepository
) : UseCase<List<ProductType>, Boolean>() {

    override suspend fun run(params: Boolean): Result<List<ProductType>> {
        return repository.getProductTypeList(params)
    }
}