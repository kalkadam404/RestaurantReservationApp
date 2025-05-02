package com.android.data.source.local

import com.android.data.model.product.ProductTypeEntity
import com.android.data.source.local.dao.ProductTypeDao

class ProductTypeLocalDataSource (private val dao: ProductTypeDao){

    suspend fun fetchProductTypeList() = dao.getAll()

    suspend fun insertProductTypeList(productTypeList: List<ProductTypeEntity>) = dao.insertAll(productTypeList)
}