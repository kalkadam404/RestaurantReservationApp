package com.example.restaurantreservation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restaurantreservation.databinding.FragmentBookBinding
import com.example.restaurantreservation.view.adapter.BookingAdapter
import com.example.restaurantreservation.view.viewmodels.ReservationListUI
import com.example.restaurantreservation.view.viewmodels.ReservationViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class BookFragment : Fragment() {

    private var _binding: FragmentBookBinding? = null
    private val binding get() = _binding!!

    private val reservationViewModel: ReservationViewModel by viewModel()
    private val bookingAdapter: BookingAdapter by lazy { BookingAdapter() } // ✅ lazy init

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentBookBinding.inflate(inflater, container, false).also { _binding = it }.root // ✅ also

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupBookingSection()

        reservationViewModel.fetchReservationList()
    }

    private fun setupBookingSection() = binding.recyclerView.apply {  // ✅ apply
        layoutManager = LinearLayoutManager(requireContext())
        adapter = bookingAdapter
    }.also {  // ✅ also (цепочка конфигурации)
        observeReservations()
    }

    private fun observeReservations() {
        reservationViewModel.reservationListUI.observe(viewLifecycleOwner) { uiState ->
            when (uiState) {
                is ReservationListUI.Success -> bookingAdapter.updateItems(uiState.reservationList)
                is ReservationListUI.Loading -> showToast("Loading reservations...") // ✅ extension
                is ReservationListUI.Error -> showToast("Error loading reservations")
                is ReservationListUI.Empty -> showToast("No reservations found")
            }
        }
    }

    private fun showToast(message: String) = Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show() // ✅ extension внутри класса

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
