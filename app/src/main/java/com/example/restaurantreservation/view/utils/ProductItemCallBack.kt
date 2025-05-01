package com.example.restaurantreservation.view.utils

import androidx.recyclerview.widget.DiffUtil
import com.android.domain.model.Product

class ProductItemCallBack : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }
}