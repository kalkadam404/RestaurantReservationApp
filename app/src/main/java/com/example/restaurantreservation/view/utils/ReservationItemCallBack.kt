package com.example.restaurantreservation.view.utils

import androidx.recyclerview.widget.DiffUtil
import com.android.domain.model.Reservation

class ReservationItemCallBack : DiffUtil.ItemCallback<Reservation>() {
    override fun areItemsTheSame(oldItem: Reservation, newItem: Reservation): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Reservation, newItem: Reservation): Boolean {
        return oldItem == newItem
    }
}