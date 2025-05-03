package com.example.restaurantreservation.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.restaurantreservation.databinding.FragmentBookBinding
import com.example.restaurantreservation.view.adapter.BookingAdapter
import com.example.restaurantreservation.view.viewmodels.ReservationListUI
import com.example.restaurantreservation.view.viewmodels.ReservationViewModel
import com.example.restaurantreservation.view.worker.ReminderWorker
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit

class BookFragment : Fragment() {

    private var _binding: FragmentBookBinding? = null
    private val binding get() = _binding!!

    private val reservationViewModel: ReservationViewModel by viewModel()
    private lateinit var bookingAdapter: BookingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupBookingSection()



        reservationViewModel.fetchReservationList()
    }

    private fun setupBookingSection() {

        bookingAdapter = BookingAdapter { reservation ->
            val dateStr = reservation.reservation_date ?: return@BookingAdapter
            val timeStr = reservation.start_time ?: return@BookingAdapter

            // Пример: "2025-05-01 18:30"
            val dateTimeStr = "$dateStr $timeStr"

            val formatter = java.text.SimpleDateFormat("yyyy-MM-dd HH:mm", java.util.Locale.getDefault())

            try {
                val bookingTime = formatter.parse(dateTimeStr)?.time ?: return@BookingAdapter
                scheduleReminder(requireContext(), bookingTime)
                Toast.makeText(requireContext(), "Напоминание установлено", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Неверный формат даты", Toast.LENGTH_SHORT).show()
            }
        }


        binding.recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = bookingAdapter

        reservationViewModel.reservationListUI.observe(viewLifecycleOwner) { uiState ->
            when (uiState) {
                is ReservationListUI.Success -> {
                    bookingAdapter.updateItems(uiState.reservationList)
                }
                is ReservationListUI.Loading -> {
                    // Optional: show loading indicator
                }
                is ReservationListUI.Error -> {
                    Toast.makeText(requireContext(), "Error loading reservations", Toast.LENGTH_SHORT).show()
                }
                is ReservationListUI.Empty -> {
                    Toast.makeText(requireContext(), "No reservations found", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

//    после успешной брони вызвать!
    fun scheduleReminder(context: Context, bookingTime: Long) {
        val delay = bookingTime - System.currentTimeMillis() - (1 * 60 * 1000)
        if (delay > 0) {
            val request = OneTimeWorkRequestBuilder<ReminderWorker>()
                .setInitialDelay(delay, TimeUnit.MILLISECONDS)
                .build()

            WorkManager.getInstance(context).enqueue(request)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
