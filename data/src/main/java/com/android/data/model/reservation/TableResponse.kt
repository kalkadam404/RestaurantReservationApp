package com.android.data.model.reservation

data class TableResponse (
    val uuid: String?,
    val number: Int,
    val section: String?,
    val qr: String,
    val call_waiter: Boolean,
    val call_time: String?,
    val bill_waiter: Boolean,
    val bill_time: String?,
    val iiko_waiter_id: String?
)