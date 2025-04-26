package com.example.restaurantreservation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.domain.model.StoryCard
import com.android.domain.model.DishItem
import com.example.restaurantreservation.R
import com.example.restaurantreservation.databinding.FragmentHomeBinding
import com.example.restaurantreservation.view.adapter.*
import com.example.restaurantreservation.view.viewmodels.RestaurantListUI
import com.example.restaurantreservation.view.viewmodels.RestaurantViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val restaurantViewModel: RestaurantViewModel by viewModel()

    private lateinit var restaurantAdapter: RestaurantAdapter

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
    }

    private fun setupBannerSection() {
        val banners = listOf(
            BannerItem(R.drawable.banner_burger, "1+1 на бургеры"),
            BannerItem(R.drawable.banner_pizza, "Только сегодня!")
        )

        val adapter = BannerAdapter(banners)
        binding.bannerRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.bannerRecyclerView.adapter = adapter
    }

    private fun setupPopularDishesSection() {
        val dishes = listOf(
            DishItem(R.drawable.plov, "Плов с курицой", "Max Plov & Рестораны"),
            DishItem(R.drawable.shashlyk, "Шашлык люля", "Max Plov & Рестораны"),
            DishItem(R.drawable.pizza_max, "Пицца грибы", "Alzans house")
        )

        val adapter = DishAdapter(dishes)
        binding.popularDishesRecycler.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.popularDishesRecycler.adapter = adapter
    }

    private fun setupRestaurantsAlmatySection() {
        restaurantAdapter = RestaurantAdapter() // <--- No emptyList() needed anymore
        binding.restaurantsAlmaty.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.restaurantsAlmaty.adapter = restaurantAdapter

        restaurantViewModel.restaurantListUI.observe(viewLifecycleOwner) { uiState ->
            when (uiState) {
                is RestaurantListUI.Success -> {
                    restaurantAdapter.updateItems(uiState.movieList)
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
