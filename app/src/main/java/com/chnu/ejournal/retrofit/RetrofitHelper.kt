package com.chnu.ejournal.retrofit

import android.content.Context
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val userAgent = "Your User Agent"

private fun newHttpClient(context: Context): OkHttpClient = OkHttpClient.Builder()
        .followRedirects(true)
        .followSslRedirects(true)
        .retryOnConnectionFailure(true)
        .setCookieInterceptors(context)
        .addNetworkInterceptor { chain ->
            val request = chain.request().newBuilder()
                    .header("User-Agent", userAgent)
                    .build()
            chain.proceed(request)
        }
        .cache(null)
        .build()

fun <T> newRetrofit(context: Context, baseUrl: String, service: Class<T>): T = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        //.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(newHttpClient(context))
        .build()
        .create(service)
