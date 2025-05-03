package com.example.restaurantreservation.view.utils

import androidx.recyclerview.widget.DiffUtil
import com.android.domain.model.Restaurant

class RestaurantDetailsItemCallBack : DiffUtil.ItemCallback<Restaurant>() {
    override fun areItemsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
        return oldItem == newItem
    }
}