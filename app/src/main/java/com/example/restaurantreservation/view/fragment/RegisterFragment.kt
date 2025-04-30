package com.example.restaurantreservation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.restaurantreservation.R
import com.example.restaurantreservation.databinding.FragmentRegisterBinding
import com.example.restaurantreservation.view.viewmodels.AuthUI
import com.example.restaurantreservation.view.viewmodels.AuthViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AuthViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()

        binding.registerButton.setOnClickListener {
            if (validateInputs()) {
                registerUser()
            }
        }

        binding.backToLoginButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setupObservers() {
        viewModel.registerResult.observe(viewLifecycleOwner) { result ->
            when (result) {
                is AuthUI.Success -> {
                    hideLoading()
                    Toast.makeText(requireContext(), "Регистрация успешна! Теперь вы можете войти.", Toast.LENGTH_LONG).show()
                    findNavController().navigateUp() // Возвращаемся на экран логина
                }
                is AuthUI.Error -> {
                    hideLoading()
                    Toast.makeText(requireContext(), result.message ?: "Ошибка регистрации", Toast.LENGTH_LONG).show()
                }
                is AuthUI.Loading -> {
                    if (result.isLoading) showLoading() else hideLoading()
                }
            }
        }
    }

    private fun validateInputs(): Boolean {
        val name = binding.nameEditText.text.toString().trim()
        val lastName = binding.lastNameEditText.text.toString().trim()
        val phone = binding.phoneEditText.text.toString().trim()
        val password = binding.passwordEditText.text.toString()
        val confirmPassword = binding.confirmPasswordEditText.text.toString()

        if (name.isEmpty() || lastName.isEmpty() || phone.isEmpty() || password.isEmpty()) {
            Toast.makeText(requireContext(), "Пожалуйста, заполните все обязательные поля", Toast.LENGTH_SHORT).show()
            return false
        }

        if (phone.length < 10) {
            Toast.makeText(requireContext(), "Пожалуйста, введите корректный номер телефона", Toast.LENGTH_SHORT).show()
            return false
        }

        if (password != confirmPassword) {
            Toast.makeText(requireContext(), "Пароли не совпадают", Toast.LENGTH_SHORT).show()
            return false
        }

        if (password.length < 6) {
            Toast.makeText(requireContext(), "Пароль должен содержать минимум 6 символов", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    private fun registerUser() {
        val userData = mapOf(
            "name" to binding.nameEditText.text.toString().trim(),
            "last_name" to binding.lastNameEditText.text.toString().trim(),
            "phone_number" to binding.phoneEditText.text.toString().trim(),
            "email" to binding.emailEditText.text.toString().trim(),
            "password" to binding.passwordEditText.text.toString()
        )

        viewModel.register(userData)
    }

    private fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
        binding.registerButton.isEnabled = false
    }

    private fun hideLoading() {
        binding.progressBar.visibility = View.GONE
        binding.registerButton.isEnabled = true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}