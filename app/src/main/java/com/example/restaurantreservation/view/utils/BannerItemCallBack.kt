package com.example.restaurantreservation.view.utils

import androidx.recyclerview.widget.DiffUtil
import com.android.domain.model.Banner


class BannerItemCallBack : DiffUtil.ItemCallback<Banner>() {
    override fun areItemsTheSame(oldItem: Banner, newItem: Banner): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Banner, newItem: Banner): Boolean {
        return oldItem == newItem
    }
}