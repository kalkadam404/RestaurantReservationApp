package com.example.restaurantreservation.view.utils

import androidx.recyclerview.widget.DiffUtil
import com.android.domain.model.ProductType

class ProductTypeItemCallBack : DiffUtil.ItemCallback<ProductType>() {
    override fun areItemsTheSame(oldItem: ProductType, newItem: ProductType): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ProductType, newItem: ProductType): Boolean {
        return oldItem == newItem
    }
}