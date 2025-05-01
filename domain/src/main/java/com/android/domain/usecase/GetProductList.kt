package com.android.domain.usecase

import com.android.domain.model.Product
import com.android.domain.repository.ProductRepository
import com.android.domain.util.UseCase


class GetProductList (
    private val repository: ProductRepository
) : UseCase<List<Product>, Boolean>() {

    override suspend fun run(params: Boolean): Result<List<Product>> {
        return repository.getProductList(params)
    }
}