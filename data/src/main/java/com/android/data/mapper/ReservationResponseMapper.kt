package com.android.data.mapper

import com.android.data.model.reservation.ReservationEntity
import com.android.data.model.reservation.ReservationResponse
import com.android.domain.model.Reservation

val ReservationResponseMapper: (ReservationResponse) -> Reservation = { response ->
    Reservation(
        id = response.id,
        restaurantName = response.restaurant_details.name,
        restaurantCityName = response.restaurant_details.city.name,
        restaurant_photo = response.restaurant_details.photo,
        tableNumber = response.table_details.number,
        tableSection = response.table_details.section,
        tableQr = response.table_details.qr,
        tableCallTime = response.table_details.call_time,
        reservation_date = response.reservation_date,
        start_time = response.start_time,
        end_time = response.end_time,
        guest_count = response.guest_count,
        guest_name = response.guest_name,
        guest_email = response.guest_email,
        guest_phone = response.guest_phone,
        status = response.status,
        status_display = response.status_display,
        special_requests = response.special_requests,
        productName = response.menu_items.firstOrNull()?.menu_item_details?.name_ru
    )
}

val localReservationResponseMapper: (ReservationEntity) -> Reservation = { response ->
    Reservation(
        id = response.id,
        restaurantName = response.restaurantName,
        restaurantCityName = response.restaurantCityName,
        restaurant_photo = response.imageUrl,
        tableNumber = response.tableNumber,
        tableSection = response.tableSection,
        tableQr = response.tableQr,
        tableCallTime = response.tableCallTime,
        reservation_date = response.reservationDate,
        start_time = response.startTime,
        end_time = response.endTime,
        guest_count = response.guestCount,
        guest_name = response.guestName,
        guest_email = response.guestEmail,
        guest_phone = response.guestPhone,
        status = response.status,
        status_display = response.statusDisplay,
        special_requests = response.specialRequests,
        productName = response.productName
    )
}

val reservationToReservationEntityMapper: (Reservation) -> ReservationEntity = { reservation ->
    ReservationEntity(
        id = reservation.id,
        restaurantName = reservation.restaurantName,
        restaurantCityName = reservation.restaurantCityName,
        tableNumber = reservation.tableNumber,
        tableSection = reservation.tableSection,
        tableQr = reservation.tableQr,
        tableCallTime = reservation.tableCallTime,
        imageUrl = reservation.restaurant_photo,
        reservationDate = reservation.reservation_date,
        startTime = reservation.start_time,
        endTime = reservation.end_time,
        guestCount = reservation.guest_count,
        guestName = reservation.guest_name,
        guestPhone = reservation.guest_phone,
        guestEmail = reservation.guest_email,
        status = reservation.status,
        statusDisplay = reservation.status_display,
        specialRequests = reservation.special_requests,
        productName = reservation.productName,
    )
}