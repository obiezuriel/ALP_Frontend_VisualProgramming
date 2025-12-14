package com.obie.alp_frontend_visualprogramming.data.repository

import com.obie.alp_frontend_visualprogramming.data.dto.CreateMoodRequest
import com.obie.alp_frontend_visualprogramming.data.dto.MoodResponseWrapper
import com.obie.alp_frontend_visualprogramming.data.service.MoodService
import retrofit2.Response

class MoodRepository(private val moodService: MoodService) {
    suspend fun createMood(userId: Int, moodType: String): Response<MoodResponseWrapper> {
        val request = CreateMoodRequest(userId, moodType)
        return moodService.createMood(request)
    }
}