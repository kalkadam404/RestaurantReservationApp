package com.example.restaurantreservation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.domain.model.Restaurant
import com.bumptech.glide.Glide
import com.example.restaurantreservation.R
import com.example.restaurantreservation.databinding.ItemRestaurantBinding

class RestaurantAdapter(private var items: List<Restaurant>) :
    RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {

    inner class RestaurantViewHolder(val binding: ItemRestaurantBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val binding = ItemRestaurantBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RestaurantViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val item = items[position]

        with(holder.binding) {
            restaurantsName.text = item.name
            restaurantsAddress.text = item.restaurantPlace

            if (!item.imageRes.isNullOrEmpty()) {
                Glide.with(root.context)
                    .load(item.imageRes)
                    .into(restaurantsImage)
            } else {
                restaurantsImage.setImageResource(R.drawable.restaurant1)
            }
        }
    }

    override fun getItemCount(): Int = items.size

    fun updateItems(newItems: List<Restaurant>) {
        items = newItems
        notifyDataSetChanged()
    }
}
