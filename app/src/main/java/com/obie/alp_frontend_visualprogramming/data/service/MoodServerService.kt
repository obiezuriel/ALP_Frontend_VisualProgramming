package com.obie.alp_frontend_visualprogramming.data.service

import com.obie.alp_frontend_visualprogramming.ui.model.CreateMoodRequest
import com.obie.alp_frontend_visualprogramming.ui.model.MoodResponseWrapper
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface MoodServerService {
    @POST("api/mood")
    suspend fun createMood(@Body request: CreateMoodRequest): Response<MoodResponseWrapper>
}