package com.chnu.ejournal

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RESTApi {
    @POST("/auth")
    fun auth(@Query("token") token: String): Call<AuthResponse>
}