package com.chnu.ejournal.retrofit

import com.chnu.ejournal.entities.GroupDTO
import com.chnu.ejournal.entities.LessonDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Body
import retrofit2.http.Path

interface RESTApi {

    @POST("/auth")
    fun auth(@Body token: String): Call<AuthResponse>

    @GET("/lessons/professor/{professorId}")
    fun getLessons(@Path("professorId") professorId: Long): Call<Set<LessonDTO>>

    @GET("/numberofweek/")
    fun getWeekNumber(): Call<Int>

    @GET("/group/{groupId}")
    fun getGroup(@Path("groupId") groupId: Long): Call<GroupDTO>


    @GET("/just_get")
    fun justGet(): Call<TestResponse>

    @GET("/secure_get")
    fun secureGet(): Call<TestResponse>
}
