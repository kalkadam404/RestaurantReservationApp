package com.example.restaurantreservation.view.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.restaurantreservation.R
import com.example.restaurantreservation.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val sharedPref by lazy {
        requireContext().getSharedPreferences("auth", Context.MODE_PRIVATE)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (!sharedPref.getBoolean("is_logged_in", false)) {
            findNavController().navigate(R.id.loginFragment)
        }

        binding.btnLogout.setOnClickListener {
            sharedPref.clearAndApply()
            findNavController().navigate(R.id.loginFragment)
        }
    }

    private fun SharedPreferences.clearAndApply() = edit().clear().apply()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
