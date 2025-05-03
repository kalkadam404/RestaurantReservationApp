package com.example.restaurantreservation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.android.domain.model.Restaurant
import com.bumptech.glide.Glide
import com.example.restaurantreservation.R
import com.example.restaurantreservation.databinding.FragmentRestaurantDetailBinding
import com.example.restaurantreservation.view.utils.RestaurantDetailsItemCallBack

class RestaurantDetailsAdapter :
    ListAdapter<Restaurant, RestaurantDetailsAdapter.RestaurantDetailViewHolder>(RestaurantDetailsItemCallBack()) {

    inner class RestaurantDetailViewHolder(val binding: FragmentRestaurantDetailBinding) :
        androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantDetailViewHolder {
        val binding = FragmentRestaurantDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RestaurantDetailViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RestaurantDetailViewHolder, position: Int) {
        val item = getItem(position)

        with(holder.binding) {
            restaurantName.text = item.name
            restaurantOpenHour.text = item.opening
            restaurantCloseHour.text = item.closing

            if (!item.imageRes.isNullOrEmpty()) {
                Glide.with(root.context)
                    .load(item.imageRes)
                    .into(imageCarousel)
            } else {
                imageCarousel.setImageResource(R.drawable.restaurant1)
            }
        }
    }

    fun updateItems(newItems: List<Restaurant>) {
        submitList(newItems)
    }
}
