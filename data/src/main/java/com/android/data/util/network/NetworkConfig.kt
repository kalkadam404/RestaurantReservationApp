package com.android.data.util.network

import okhttp3.Interceptor

data class NetworkConfig(
    val baseUrl: String,
    val timeout: Long,
    val interceptors: List<Interceptor>
)
