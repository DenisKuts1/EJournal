package com.chnu.ejournal.retrofit

import android.content.Context
import com.google.gson.GsonBuilder
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MyRetrofitApi {
    val gson = GsonBuilder().setLenient().create()
    val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    lateinit var api: RESTApi

    fun init(context: Context){
        api = newRetrofit(context, "http://10.0.2.2:8080", RESTApi::class.java)
    }

    //lateinit var secureApi:  RESTApi

    /*fun setAuthToken(authToken: String){
        secureApi = AuthenticatedServiceGenerator.createService(RESTApi::class.java, authToken)
    }*/

    fun auth(token: String) = api.auth(token).execute().also {
        // Log.i("ЬУУУУУУУУУІІІІІІІІІІуп", it.message())
    }

    fun justGet() = api.justGet().execute()
    fun secureGet(): Response<TestResponse> {
        val some = api.secureGet()
        return some.execute()
    }

}