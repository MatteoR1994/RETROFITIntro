package com.example.retrofitintro

import com.google.gson.annotations.SerializedName

data class CourseSerializedName (
    @SerializedName("id")
    val courseId: Int,
    @SerializedName("title")
    val courseTitle: String,
    @SerializedName("numHours")
    val courseNumHours: Int,
    @SerializedName("description")
    val courseDescription: String,
    @SerializedName("cost")
    val courseCost: Double
)
