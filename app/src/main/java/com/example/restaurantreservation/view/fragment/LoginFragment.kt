package com.example.restaurantreservation.view.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.restaurantreservation.R
import com.example.restaurantreservation.databinding.FragmentLoginBinding
import com.example.restaurantreservation.view.activities.MainActivity
import com.example.restaurantreservation.view.viewmodels.AuthUI
import com.example.restaurantreservation.view.viewmodels.AuthViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AuthViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()

        binding.loginButton.setOnClickListener {
            val phoneNumber = binding.phoneEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            if (phoneNumber.isNotEmpty() && password.isNotEmpty()) {
                viewModel.login(phoneNumber, password)
            } else {
                Toast.makeText(requireContext(), "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show()
            }
        }

        binding.registerTextView.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        binding.backTo.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_home_fragment)
        }

    }

    private fun setupObservers() {
        viewModel.loginResult.observe(viewLifecycleOwner) { result ->
            when (result) {
                is AuthUI.Success -> {
                    hideLoading()
                    Toast.makeText(requireContext(), "Успешный вход!", Toast.LENGTH_SHORT).show()
                    val sharedPref = requireContext().getSharedPreferences("auth", Context.MODE_PRIVATE)
                    sharedPref.edit().putBoolean("is_logged_in", true).apply()
                    Log.d("LoginFragment", "Login success, navigating...")
                    findNavController().navigate(R.id.action_loginFragment_to_profile_fragment)
                }
                is AuthUI.Error -> {
                    hideLoading()
                    Toast.makeText(requireContext(), result.message ?: "Ошибка входа", Toast.LENGTH_LONG).show()
                }
                is AuthUI.Loading -> {
                    if (result.isLoading) showLoading() else hideLoading()
                }
            }
        }
    }

    private fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
        binding.loginButton.isEnabled = false
    }

    private fun hideLoading() {
        binding.progressBar.visibility = View.GONE
        binding.loginButton.isEnabled = true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
