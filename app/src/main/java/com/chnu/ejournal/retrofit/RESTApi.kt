package com.chnu.ejournal.retrofit

import com.chnu.ejournal.entities.GroupDTO
import com.chnu.ejournal.entities.Lab
import com.chnu.ejournal.entities.LessonDTO
import com.chnu.ejournal.entities.StudentDTO
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

    @GET("/group/students/{id}")
    fun getStudentsOfGroup(@Path("id") groupId: Long): Call<List<StudentDTO>>

    @GET("lesson/tasks/{lessonId}")
    fun getLabsOfSubject(@Path("lessonId") lessonId: Long): Call<Set<Lab>>

    @GET("student/{studentId}/task/{taskId}")
    fun getPointOfStudent(@Path("studentId") studentId: Long, @Path("taskId") taskId: Long): Call<Double>

    @GET("/just_get")
    fun justGet(): Call<TestResponse>

    @GET("/secure_get")
    fun secureGet(): Call<TestResponse>
}
