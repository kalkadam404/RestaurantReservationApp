package com.android.data.source.local

import com.android.data.model.product.ProductEntity
import com.android.data.source.local.dao.ProductDao

class ProductLocalDataSource (private val dao: ProductDao){

    suspend fun fetchProductList() = dao.getAll()

    suspend fun insertProductList(productList: List<ProductEntity>) = dao.insertAll(productList)
}