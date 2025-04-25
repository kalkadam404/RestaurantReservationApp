package com.example.restaurantreservation.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.domain.model.DishItem
import com.example.restaurantreservation.R

class DishAdapter(private val items: List<DishItem>) :
    RecyclerView.Adapter<DishAdapter.DishViewHolder>() {

    inner class DishViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.dishImage)
        val name: TextView = itemView.findViewById(R.id.dishName)
        val place: TextView = itemView.findViewById(R.id.dishPlace)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_dish, parent, false)
        return DishViewHolder(view)
    }

    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {
        val item = items[position]
        holder.image.setImageResource(item.imageRes)
        holder.name.text = item.name
        holder.place.text = item.place
    }

    override fun getItemCount(): Int = items.size
}
