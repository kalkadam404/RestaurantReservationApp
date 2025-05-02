package com.android.domain.usecase

import com.android.domain.model.ProductType
import com.android.domain.repository.ProductTypeRepository
import com.android.domain.util.UseCase

class InsertProductTypeList(
    private val repository: ProductTypeRepository
) : UseCase<Unit, List<ProductType>>() {

    override suspend fun run(params: List<ProductType>): Result<Unit> {
        return repository.insertProductTypeList(params)
    }
}