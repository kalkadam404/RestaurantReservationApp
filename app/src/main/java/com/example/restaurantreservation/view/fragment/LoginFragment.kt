package com.example.restaurantreservation.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.restaurantreservation.R
import com.example.restaurantreservation.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.loginButton.setOnClickListener {
//            val email = binding.emailEditText.text.toString()
//            val password = binding.passwordEditText.text.toString()
//
//            if (email.isNotEmpty() && password.isNotEmpty()) {
//                login(email, password)
//            } else {
//                Toast.makeText(requireContext(), "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show()
//            }
//        }
    }

    private fun login(email: String, password: String) {
        // Здесь пока мок-авторизация. Потом сюда подключим API
        if (email == "test@example.com" && password == "password") {
            val sharedPref = requireContext().getSharedPreferences("auth", Context.MODE_PRIVATE)
            sharedPref.edit().putBoolean("is_logged_in", true).apply()
            Toast.makeText(requireContext(), "Успешный вход!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.profile_fragment) // Вернуться обратно на профиль
        } else {
            Toast.makeText(requireContext(), "Неверные данные", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
