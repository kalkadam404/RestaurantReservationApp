package com.example.restaurantreservation.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.android.domain.model.MenuItem
import com.example.restaurantreservation.R

import com.example.restaurantreservation.databinding.FragmentMenuBinding
import com.example.restaurantreservation.view.adapter.MenuAdapter


class MenuFragment : Fragment() {
    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuItems = listOf(
            MenuItem.SectionHeader("Салаты"),
            MenuItem.DishItem("Салат Цезарь с Курицей", "2690 ₸", R.drawable.salad1),
            MenuItem.DishItem("Салат с Хрустящими Баклажанами", "2290 ₸", R.drawable.salad2),
            MenuItem.DishItem("Салат 'Греческий'", "2890 ₸", R.drawable.salad3),
            MenuItem.DishItem("Салат с Говядиной", "2990 ₸", R.drawable.salad4),

            MenuItem.SectionHeader("Супы"),
            MenuItem.DishItem("Чечевичный Суп", "2190 ₸", R.drawable.sup1),
            MenuItem.DishItem("Суп с Овощами", "2310 ₸", R.drawable.sup2)
        )

        binding.menuRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.menuRecycler.adapter = MenuAdapter(menuItems)
    }


}