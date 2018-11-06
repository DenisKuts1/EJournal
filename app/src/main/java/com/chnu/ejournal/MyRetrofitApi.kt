package com.chnu.ejournal

import android.util.Log
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object MyRetrofitApi {
    val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    val api = retrofit.create(RESTApi::class.java)

    fun auth(token: String) = api.auth(token).execute().also {
       // Log.i("ЬУУУУУУУУУІІІІІІІІІІуп", it.message())
    }

    fun justGet() = api.justGet().execute()
    fun secureGet() = api.secureGet().execute()

    fun getNews(after: String, limit: String) = api.getTop(after, limit).execute()
}