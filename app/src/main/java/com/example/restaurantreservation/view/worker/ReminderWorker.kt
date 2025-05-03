package com.example.restaurantreservation.view.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.restaurantreservation.view.utils.NotificationUtils

class ReminderWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    override fun doWork(): Result {
        NotificationUtils.showNotification(
            applicationContext,
            "Напоминание",
            "У вас скоро бронирование ресторана!"
        )
        return Result.success()
    }
}
