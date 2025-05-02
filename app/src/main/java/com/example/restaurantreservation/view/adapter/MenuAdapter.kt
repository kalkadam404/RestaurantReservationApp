package com.example.restaurantreservation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.android.domain.model.Product
import com.bumptech.glide.Glide
import com.example.restaurantreservation.R
import com.example.restaurantreservation.databinding.ItemDishCategoryBinding
import com.example.restaurantreservation.view.utils.ProductItemCallBack

class MenuAdapter(private val onDishClick: (Product) -> Unit) :
    ListAdapter<Product, MenuAdapter.MenuViewHolder>(ProductItemCallBack()) {

    inner class MenuViewHolder(val binding: ItemDishCategoryBinding) : androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val binding = ItemDishCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MenuViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val item = getItem(position)

        with(holder.binding) {
            PriceDish.text = item.price.toString()
            NameDish.text = item.ru_name


            if (!item.imageUrl.isNullOrEmpty()) {
                Glide.with(root.context)
                    .load(item.imageUrl)
                    .into(ImgDish)
            } else {
                ImgDish.setImageResource(R.drawable.restaurant1)
            }
            root.setOnClickListener {
                onDishClick(item)
            }
        }
    }

    fun updateItems(newItems: List<Product>) {
        submitList(newItems)
    }
}
