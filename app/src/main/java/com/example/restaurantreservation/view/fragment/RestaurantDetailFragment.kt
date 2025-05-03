package com.example.restaurantreservation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.domain.model.Restaurant
import com.bumptech.glide.Glide
import com.example.restaurantreservation.R
import com.example.restaurantreservation.databinding.FragmentRestaurantDetailBinding
import com.example.restaurantreservation.view.viewmodels.RestaurantDetailUI
import com.example.restaurantreservation.view.viewmodels.RestaurantDetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RestaurantDetailFragment : Fragment() {

    private val restaurantDetailsViewModel: RestaurantDetailsViewModel by viewModel()
    private var _binding: FragmentRestaurantDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val restaurantId = RestaurantDetailFragmentArgs.fromBundle(it).restaurant.id
            restaurantDetailsViewModel.fetchRestaurantDetail(restaurantId, force = true)
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

        // Observe the restaurant detail UI state
        restaurantDetailsViewModel.restaurantDetailUI.observe(viewLifecycleOwner) { uiState ->
            when (uiState) {
                is RestaurantDetailUI.Loading -> {
                    // Show loading state

                }
                is RestaurantDetailUI.Success -> {
                    // Display restaurant details
                    displayRestaurantDetails(uiState.restaurantDetail)
                }
                is RestaurantDetailUI.Error -> {
                    // Show error message
                    Toast.makeText(requireContext(), "Error loading restaurant details: ${uiState.message}", Toast.LENGTH_SHORT).show()
                }
                is RestaurantDetailUI.Empty -> {
                    // Handle empty state
                    Toast.makeText(requireContext(), "No restaurant details available", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // Back button listener
        binding.btnBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun displayRestaurantDetails(restaurant: Restaurant) {
        // Set restaurant name, hours, and other details
        binding.restaurantName.text = restaurant.name
        binding.restaurantOpenHour.text = restaurant.opening
        binding.restaurantCloseHour.text = restaurant.closing

        // Load restaurant image using Glide (if available)
        if (!restaurant.imageRes.isNullOrEmpty()) {
            Glide.with(requireContext())
                .load(restaurant.imageRes)
                .into(binding.imageCarousel)
        } else {
            // Set a default image if no image is provided
            binding.imageCarousel.setImageResource(R.drawable.restaurant1)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
