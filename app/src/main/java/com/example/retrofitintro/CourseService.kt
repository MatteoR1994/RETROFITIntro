package com.example.retrofitintro

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CourseService {
    @GET("course/")
    fun getCourses(): Call<List<CourseSerializedName>>

    @GET("course/{id}")
    fun getCourseById(@Path("id") id: Int): Call<CourseSerializedName>
}