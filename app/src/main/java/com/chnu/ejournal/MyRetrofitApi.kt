package com.chnu.ejournal

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object MyRetrofitApi {
    val retrofit = Retrofit.Builder()
            .baseUrl("http://localhost")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    val api = retrofit.create(RESTApi::class.java)

    fun auth(token: String) = api.auth(token).execute()

    fun getNews(after: String, limit: String) = api.getTop(after, limit).execute()
}