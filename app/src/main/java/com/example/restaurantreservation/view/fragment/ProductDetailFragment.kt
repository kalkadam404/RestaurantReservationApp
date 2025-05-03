package com.example.restaurantreservation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.android.domain.model.Product
import com.example.restaurantreservation.R
import android.util.Log
import com.example.restaurantreservation.databinding.FragmentDishDetailBinding
import com.example.restaurantreservation.view.viewmodels.ProductDetailUI
import com.example.restaurantreservation.view.viewmodels.ProductDetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductDetailFragment : Fragment() {

    private val productDetailsViewModel: ProductDetailsViewModel by viewModel()
    private var _binding: FragmentDishDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val productId = ProductDetailFragmentArgs.fromBundle(it).product.id
            Log.d("ProductDetailFragment", "Product ID: $productId")
            productDetailsViewModel.fetchProductDetail(productId, force = true)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDishDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Observe the product detail UI state
        productDetailsViewModel.productDetailUI.observe(viewLifecycleOwner) { uiState ->
            when (uiState) {
                is ProductDetailUI.Loading -> {
                    // Show loading state

                }
                is ProductDetailUI.Success -> {
                    // Display product details
                    displayProductDetails(uiState.productDetail)
                }
                is ProductDetailUI.Error -> {
                    // Show error message
                    Toast.makeText(requireContext(), "Error loading product details: ${uiState.message}", Toast.LENGTH_SHORT).show()
                }
                is ProductDetailUI.Empty -> {
                    // Handle empty state
                    Toast.makeText(requireContext(), "No product details available", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // Back button listener
        binding.toolbar.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun displayProductDetails(product: Product) {
        // Set product name, description, etc.
        binding.dishName.text = product.ru_name
        binding.dishLocation.text = product.location
        binding.dishDescription.text = product.ru_description

        // Load product image using Glide (if available)
        if (!product.imageUrl.isNullOrEmpty()) {
            Glide.with(requireContext())
                .load(product.imageUrl)
                .into(binding.dishImage)
        } else {
            // Set a default image if no image is provided
            binding.dishImage.setImageResource(R.drawable.restaurant1)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
