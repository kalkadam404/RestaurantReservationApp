package com.android.data.util.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient {

    fun <T> createApiService(
        config: NetworkConfig,
        serviceClass: Class<T>
    ): T {
        return Retrofit.Builder()
            .baseUrl(config.baseUrl)
            .client(
                OkHttpClient.Builder()
                    .connectTimeout(config.timeout, TimeUnit.SECONDS)
                    .readTimeout(config.timeout, TimeUnit.SECONDS)
                    .writeTimeout(config.timeout, TimeUnit.SECONDS)
                    .apply {
                        addInterceptor(HttpLoggingInterceptor().apply {
                            level = HttpLoggingInterceptor.Level.BODY
                        })
                        config.interceptors.forEach {
                            addInterceptor(it)
                        }
                    }
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(serviceClass)
    }
}
