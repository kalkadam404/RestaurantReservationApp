package com.example.restaurantreservation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.domain.model.Restaurant
import com.example.restaurantreservation.databinding.FragmentRestaurantsListBinding
import com.example.restaurantreservation.view.adapter.RestaurantAdapter

class RestaurantsListFragment : Fragment() {

    private var _binding: FragmentRestaurantsListBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: RestaurantAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRestaurantsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view,savedInstanceState)

        val restaurantList = listOf(
            Restaurant(
                id = 1,
                imageRes = "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/11/38/b9/3f/getlstd-property-photo.jpg?w=600&h=-1&s=1",
                name = "La Piazza",
                restaurantPlace = "г. Алматы, ул. Абая 44",
                opening = "09:00",
                closing = "23:00"
            ),
            Restaurant(
                id = 2,
                imageRes = "https://restolife.kz/upload/information_system_6/1/6/0/item_16046/information_items_property_29882.jpg",
                name = "Tokyo Sushi",
                restaurantPlace = "г. Нур-Султан, пр. Тауельсиздик 17",
                opening = "10:00",
                closing = "22:00"
            ),
            Restaurant(
                id = 3,
                imageRes = "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/1c/19/75/ca/navat.jpg?w=600&h=-1&s=1",
                name = "Burger House",
                restaurantPlace = "г. Шымкент, ул. Байтурсынова 12",
                opening = "11:00",
                closing = "01:00"
            ),
            Restaurant(
                id = 4,
                imageRes = "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/1a/38/f3/45/caption.jpg?w=600&h=400&s=1",
                name = "Восточная сказка",
                restaurantPlace = "г. Караганда, ул. Ермекова 5",
                opening = "12:00",
                closing = "00:00"
            )
        )

        adapter = RestaurantAdapter { restaurant ->
            val action = RestaurantsListFragmentDirections.actionRestaurantsListFragmentToRestaurantDetailFragment(restaurant)
            findNavController().navigate(action)
        }

        binding.recyclerViewRestaurants.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewRestaurants.adapter = adapter
        adapter.submitList(restaurantList)

        binding.backIcon.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
