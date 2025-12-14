package com.obie.alp_frontend_visualprogramming.data.service

import com.obie.alp_frontend_visualprogramming.data.dto.CreateMoodRequest
import com.obie.alp_frontend_visualprogramming.data.dto.MoodResponseWrapper
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface MoodService {
    @POST("mood")
    suspend fun createMood(@Body request: CreateMoodRequest): Response<MoodResponseWrapper>
}