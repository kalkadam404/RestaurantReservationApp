package com.example.restaurantreservation.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.android.domain.model.Restaurant
import com.example.restaurantreservation.R
import com.example.restaurantreservation.databinding.FragmentRestaurantDetailBinding
import com.example.restaurantreservation.view.adapter.ImageCarouselAdapter
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator

class RestaurantDetailFragment : Fragment() {

    private lateinit var restaurant: Restaurant
    private lateinit var viewPager: ViewPager2

    private var _binding: FragmentRestaurantDetailBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            restaurant = RestaurantDetailFragmentArgs.fromBundle(it).restaurant
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRestaurantDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val images = listOf(
            R.drawable.restaurant_full1,
            R.drawable.restaurant_full2,
            R.drawable.restaurant_full3
        )

        val imageCarouselAdapter = ImageCarouselAdapter(images)
        binding.imageCarousel.adapter = imageCarouselAdapter

        viewPager = binding.imageCarousel
        val springDotsIndicator = binding.springDotsIndicator
        springDotsIndicator.attachTo(viewPager)

        binding.btnBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}