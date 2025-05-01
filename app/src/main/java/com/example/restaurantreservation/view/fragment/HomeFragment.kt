package com.example.restaurantreservation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.domain.model.StoryCard
import com.example.restaurantreservation.R
import com.example.restaurantreservation.databinding.FragmentHomeBinding
import com.example.restaurantreservation.view.adapter.*
import com.example.restaurantreservation.view.viewmodels.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val restaurantViewModel: RestaurantViewModel by viewModel()
    private val productViewModel: ProductViewModel by viewModel()
    private val bannerViewModel: BannerViewModel by viewModel()

    private lateinit var restaurantAdapter: RestaurantAdapter
    private lateinit var dishAdapter: DishAdapter
    private lateinit var bannerAdapter: BannerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val storyCards = listOf(
            StoryCard(R.drawable.logo, "Наш новый логотип"),
            StoryCard(R.drawable.trad, "Традиционная кухня"),
            StoryCard(R.drawable.personal, "Заботливый сервис"),
            StoryCard(R.drawable.pizza, "Аутентичный атмосфера")
        )

        val storyAdapter = StoryAdapter(storyCards)
        binding.storiesRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = storyAdapter
        }

        setupBannerSection()
        setupPopularDishesSection()
        setupRestaurantsAlmatySection()

        restaurantViewModel.fetchRestaurantList()
        productViewModel.fetchProductList()
        bannerViewModel.fetchBannerList()
    }

    private fun setupBannerSection() {
        bannerAdapter = BannerAdapter()
        binding.bannerRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.bannerRecyclerView.adapter = bannerAdapter

        bannerViewModel.bannerListUI.observe(viewLifecycleOwner) { uiState ->
            when (uiState) {
                is BannerListUI.Success -> {
                    bannerAdapter.updateItems(uiState.bannerList)
                }
                is BannerListUI.Loading -> {
                    // Optional: show loading indicator
                }
                is BannerListUI.Error -> {
                    // Optional: show error message
                }
                is BannerListUI.Empty -> {
                    // Optional: show empty state
                }
            }
        }
    }

    private fun setupPopularDishesSection() {
        dishAdapter = DishAdapter()
        binding.popularDishesRecycler.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.popularDishesRecycler.adapter = dishAdapter

        productViewModel.productListUI.observe(viewLifecycleOwner) { uiState ->
            when (uiState) {
                is ProductListUI.Success -> {
                    dishAdapter.updateItems(uiState.productList)
                }
                is ProductListUI.Loading -> {
                    // Optional: show loading indicator
                }
                is ProductListUI.Error -> {
                    // Optional: show error message
                }
                is ProductListUI.Empty -> {
                    // Optional: show empty state
                }
            }
        }
    }

    private fun setupRestaurantsAlmatySection() {
        restaurantAdapter = RestaurantAdapter()
        binding.restaurantsAlmaty.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.restaurantsAlmaty.adapter = restaurantAdapter

        restaurantViewModel.restaurantListUI.observe(viewLifecycleOwner) { uiState ->
            when (uiState) {
                is RestaurantListUI.Success -> {
                    restaurantAdapter.updateItems(uiState.restaurantList)
                }
                is RestaurantListUI.Loading -> {
                    // Show loading spinner if needed
                }
                is RestaurantListUI.Error -> {
                    // Show error message
                }
                is RestaurantListUI.Empty -> {
                    // Show empty state view
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
