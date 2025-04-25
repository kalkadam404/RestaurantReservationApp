package com.example.restaurantreservation.view.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.domain.model.Restaurant
import com.example.restaurantreservation.R

class RestaurantAdapter(private val items: List<Restaurant>) :
    RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {

    inner class RestaurantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.restaurantsImage)
        val name: TextView = itemView.findViewById(R.id.restaurantsName)
        val restaurantPlace: TextView = itemView.findViewById(R.id.restaurantsAddress)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_restaurant, parent, false)
        return RestaurantViewHolder(view)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val item = items[position]
        holder.image.setImageResource(item.imageRes)
        holder.name.text = item.name
        holder.restaurantPlace.text = item.restaurantPlace
    }

    override fun getItemCount(): Int = items.size
}

