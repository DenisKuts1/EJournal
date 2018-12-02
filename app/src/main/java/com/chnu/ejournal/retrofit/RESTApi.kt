package com.chnu.ejournal.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Body

interface RESTApi {

    @POST("/auth")
    fun auth(@Body token: String): Call<AuthResponse>

    @GET("/just_get")
    fun justGet(): Call<TestResponse>

    @GET("/secure_get")
    fun secureGet(): Call<TestResponse>
}
