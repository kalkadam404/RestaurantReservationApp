package com.example.restaurantreservation.view.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.android.domain.model.ProductType
import com.example.restaurantreservation.databinding.ItemCategoryButtonBinding
import com.example.restaurantreservation.view.utils.ProductTypeItemCallBack

class ProductTypeAdapter :
    ListAdapter<ProductType, ProductTypeAdapter.ProductTypeViewHolder>(ProductTypeItemCallBack()) {

    inner class ProductTypeViewHolder(val binding: ItemCategoryButtonBinding) : androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductTypeViewHolder {
        val binding = ItemCategoryButtonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductTypeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductTypeViewHolder, position: Int) {
        val item = getItem(position)

        with(holder.binding) {
            productTypeName.text = item.title

        }
    }
    fun updateItems(newItems: List<ProductType>) {
        submitList(newItems)
    }
}
