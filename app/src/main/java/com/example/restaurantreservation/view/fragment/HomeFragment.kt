package com.example.restaurantreservation.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.domain.model.DishItem
import com.android.domain.model.Restaurant
import com.android.domain.model.StoryCard
import com.example.restaurantreservation.R
import com.example.restaurantreservation.databinding.FragmentHomeBinding
import com.example.restaurantreservation.view.adapter.BannerItem
import com.example.restaurantreservation.view.adapter.StoryAdapter
import com.example.restaurantreservation.view.adapter.BannerAdapter
import com.example.restaurantreservation.view.adapter.DishAdapter
import com.example.restaurantreservation.view.adapter.RestaurantAdapter

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

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
    }

    private fun setupBannerSection() {
        val banners = listOf(
            BannerItem(R.drawable.banner_burger, "1+1 на бургеры"),
            BannerItem(R.drawable.banner_pizza, "Только сегодня!"),
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
        val restaurants = listOf(
            Restaurant(R.drawable.restaurant1, "Qaimaq", "Проспект Абая, 46а Алматы"),
            Restaurant(R.drawable.restaurant2, "Chaihana NAVAT", "Проспект Абылай хана, 58а Алматы"),
            Restaurant(R.drawable.restaurant3, "Sansara Lounge", "улица Наурызбай батыра, 85 Алматы"),
            Restaurant(R.drawable.restaurant4, "Candy Bar", "Болат Бабатайұлы, 84 Алматы"),
        )

        val adapter = RestaurantAdapter(restaurants)
        binding.restaurantsAlmaty.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.restaurantsAlmaty.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}