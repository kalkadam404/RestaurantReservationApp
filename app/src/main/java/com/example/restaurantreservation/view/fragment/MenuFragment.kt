package com.example.restaurantreservation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restaurantreservation.databinding.FragmentMenuBinding
import com.example.restaurantreservation.view.adapter.MenuAdapter
import com.example.restaurantreservation.view.adapter.ProductTypeAdapter
import com.example.restaurantreservation.view.viewmodels.ProductListUI
import com.example.restaurantreservation.view.viewmodels.ProductTypeListUI
import com.example.restaurantreservation.view.viewmodels.ProductTypeViewModel
import com.example.restaurantreservation.view.viewmodels.ProductViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MenuFragment : Fragment() {

    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    private val productViewModel: ProductViewModel by viewModel()
    private val productTypeViewModel: ProductTypeViewModel by viewModel()

    private lateinit var menuAdapter: MenuAdapter
    private lateinit var categoryAdapter: ProductTypeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupCategorySection()
        setupDishSection()

        productTypeViewModel.fetchProductTypeList()
        productViewModel.fetchProductList()
    }

    private fun setupCategorySection() {
        categoryAdapter = ProductTypeAdapter()
        binding.productTypeRecycler.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.productTypeRecycler.adapter = categoryAdapter

        productTypeViewModel.productTypeListUI.observe(viewLifecycleOwner) { uiState ->
            when (uiState) {
                is ProductTypeListUI.Success -> {
                    categoryAdapter.updateItems(uiState.productTypeList)
                }
                is ProductTypeListUI.Loading -> {
                    // Optional: show loading
                }
                is ProductTypeListUI.Error -> {
                    Toast.makeText(requireContext(), "Error loading categories", Toast.LENGTH_SHORT).show()
                }
                is ProductTypeListUI.Empty -> {
                    // Handle empty category state
                }
            }
        }
    }

    private fun setupDishSection() {
        menuAdapter = MenuAdapter { product ->
            // TODO: Navigate to product detail if needed
        }
        binding.menuRecycler.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.menuRecycler.adapter = menuAdapter

        productViewModel.productListUI.observe(viewLifecycleOwner) { uiState ->
            when (uiState) {
                is ProductListUI.Success -> {
                    menuAdapter.updateItems(uiState.productList)
                }
                is ProductListUI.Loading -> {
                    //
                }
                is ProductListUI.Error -> {
                    Toast.makeText(requireContext(), "Error loading products", Toast.LENGTH_SHORT).show()
                }
                is ProductListUI.Empty -> {
                    // Handle empty dish list
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
