package com.example.restaurantreservation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.android.domain.model.Banner
import com.bumptech.glide.Glide
import com.example.restaurantreservation.R
import com.example.restaurantreservation.databinding.ItemBannerBinding
import com.example.restaurantreservation.view.utils.BannerItemCallBack

class BannerAdapter :
    ListAdapter<Banner, BannerAdapter.BannerViewHolder>(BannerItemCallBack()) {

    inner class BannerViewHolder(val binding: ItemBannerBinding) : androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val binding = ItemBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BannerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        val item = getItem(position)

        with(holder.binding) {
            bannerText.text = item.name

            if (!item.imageUrl.isNullOrEmpty()) {
                Glide.with(root.context)
                    .load(item.imageUrl)
                    .into(bannerImage)
            } else {
                bannerImage.setImageResource(R.drawable.restaurant1)
            }
        }
    }

    fun updateItems(newItems: List<Banner>) {
        submitList(newItems)
    }
}