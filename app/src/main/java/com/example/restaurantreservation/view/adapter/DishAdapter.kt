package com.example.restaurantreservation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.android.domain.model.Product
import com.bumptech.glide.Glide
import com.example.restaurantreservation.R
import com.example.restaurantreservation.databinding.ItemDishBinding
import com.example.restaurantreservation.view.utils.ProductItemCallBack

class DishAdapter :
    ListAdapter<Product, DishAdapter.DishViewHolder>(ProductItemCallBack()) {

    inner class DishViewHolder(val binding: ItemDishBinding) : androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder {
        val binding = ItemDishBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DishViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {
        val item = getItem(position)

        with(holder.binding) {
            dishName.text = item.ru_name
            dishPlace.text = item.location


            if (!item.imageUrl.isNullOrEmpty()) {
                Glide.with(root.context)
                    .load(item.imageUrl)
                    .into(dishImage)
            } else {
                dishImage.setImageResource(R.drawable.restaurant1)
            }
        }
    }

    fun updateItems(newItems: List<Product>) {
        submitList(newItems)
    }
}
