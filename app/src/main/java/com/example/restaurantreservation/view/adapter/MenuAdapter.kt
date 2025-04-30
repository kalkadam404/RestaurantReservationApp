package com.example.restaurantreservation.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.domain.model.MenuItem
import com.example.restaurantreservation.R

class MenuAdapter(private val items: List<MenuItem>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_HEADER = 0
        private const val VIEW_TYPE_DISH = 1
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is MenuItem.SectionHeader -> VIEW_TYPE_HEADER
            is MenuItem.DishItem -> VIEW_TYPE_DISH
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_HEADER -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_category_header, parent, false)
                SectionHeaderViewHolder(view)
            }
            VIEW_TYPE_DISH -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_dish_category, parent, false)
                DishViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = items[position]) {
            is MenuItem.SectionHeader -> (holder as SectionHeaderViewHolder).bind(item)
            is MenuItem.DishItem -> (holder as DishViewHolder).bind(item)
        }
    }

    override fun getItemCount() = items.size

    class SectionHeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val title: TextView = view.findViewById(R.id.sectionTitle)
        fun bind(item: MenuItem.SectionHeader) {
            title.text = item.title
        }
    }

    class DishViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val name: TextView = view.findViewById(R.id.NameDish)
        private val price: TextView = view.findViewById(R.id.PriceDish)
        private val image: ImageView = view.findViewById(R.id.ImgDish)

        fun bind(item: MenuItem.DishItem) {
            name.text = item.name
            price.text = item.price
            image.setImageResource(item.imageResId)
        }
    }
}
