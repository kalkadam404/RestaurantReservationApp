package com.android.data.source.remote.api
import com.android.data.model.AuthRequest
import com.android.data.model.AuthResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("api/v1/users/token/obtain")
    suspend fun login(@Body request: AuthRequest): Response<AuthResponse>

    @POST("api/v1/users/register/")
    suspend fun register(@Body request: Map<String, Any>): Response<Any>
}