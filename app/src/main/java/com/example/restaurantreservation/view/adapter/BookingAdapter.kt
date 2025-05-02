package com.example.restaurantreservation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.android.domain.model.Reservation
import com.bumptech.glide.Glide
import com.example.restaurantreservation.R
import com.example.restaurantreservation.databinding.ItemRestaurantForBookingBinding
import com.example.restaurantreservation.view.utils.ReservationItemCallBack

class BookingAdapter :
    ListAdapter<Reservation, BookingAdapter.BookingViewHolder>(ReservationItemCallBack()) {

    inner class BookingViewHolder(val binding: ItemRestaurantForBookingBinding) : androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingViewHolder {
        val binding = ItemRestaurantForBookingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookingViewHolder, position: Int) {
        val item = getItem(position)

        with(holder.binding) {
            restaurantsName.text = item.restaurantName
            restaurantsAddress.text = item.restaurantCityName

            if (!item.restaurant_photo.isNullOrEmpty()) {
                Glide.with(root.context)
                    .load(item.restaurant_photo)
                    .into(restaurantsImage)
            } else {
                restaurantsImage.setImageResource(R.drawable.restaurant1)
            }
        }
    }

    fun updateItems(newItems: List<Reservation>) {
        submitList(newItems)
    }
}
