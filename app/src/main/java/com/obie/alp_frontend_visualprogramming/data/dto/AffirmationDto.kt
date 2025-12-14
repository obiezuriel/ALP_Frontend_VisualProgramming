package com.obie.alp_frontend_visualprogramming.data.dto

import com.google.gson.annotations.SerializedName

//request
data class CreateMoodRequest (
    @SerializedName("user_id")
    val userId: Int,

    @SerializedName("mood_type")
    val moodType: String
)

//response
data class MoodResponseWrapper (
    val success : Boolean,
    val data    : MoodData
)

//data
data class MoodData (
    val id: Int,

    @SerializedName("mood_type")
    val moodType: String,

    @SerializedName("affirmation_text")
    val affirmationText: String,

    @SerializedName("created_at")
    val createdAt: String
)

