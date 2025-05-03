package com.example.restaurantreservation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.android.domain.model.Product
import com.bumptech.glide.Glide
import com.example.restaurantreservation.R
import com.example.restaurantreservation.databinding.FragmentDishDetailBinding
import com.example.restaurantreservation.view.utils.ProductDetailsItemCallBack

class ProductDetailsAdapter() :
    ListAdapter<Product, ProductDetailsAdapter.ProductDetailsViewHolder>(ProductDetailsItemCallBack()) {

    inner class ProductDetailsViewHolder(val binding: FragmentDishDetailBinding) :
        androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductDetailsViewHolder {
        val binding = FragmentDishDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductDetailsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductDetailsViewHolder, position: Int) {
        val item = getItem(position)

        with(holder.binding) {
            dishName.text = item.ru_name
            dishLocation.text = item.location
            dishDescription.text = item.ru_description


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
