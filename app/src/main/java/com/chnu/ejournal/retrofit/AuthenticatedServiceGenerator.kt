package com.chnu.ejournal.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AuthenticatedServiceGenerator {
    private const val BASE_URL = "http://10.0.2.2:8080"
    private val httpClient = OkHttpClient.Builder()
    private lateinit var retrofit: Retrofit

    private val builder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

    fun <S> createService(serviceClass: Class<S>): S = createService(serviceClass, "")

    fun <S> createService(serviceClass: Class<S>, authToken: String): S{
        if(authToken.isNotEmpty()){
            val interceptor = AuthenticationInterceptor(authToken)
            if(!httpClient.interceptors().contains(interceptor)){
                httpClient.addInterceptor(interceptor)
                builder.client(httpClient.build())
                retrofit = builder.build()
            }
        }
        return retrofit.create(serviceClass)
    }

}