package com.obie.alp_frontend_visualprogramming.data.repository

import com.obie.alp_frontend_visualprogramming.data.service.MoodServerService
import com.obie.alp_frontend_visualprogramming.ui.model.CreateMoodRequest
import com.obie.alp_frontend_visualprogramming.ui.model.MoodResponseWrapper
import retrofit2.Response

class MoodServerRepository(private val service: MoodServerService) {
    suspend fun createMood(userId: Int, moodType: String): Response<MoodResponseWrapper> {
        return service.createMood(CreateMoodRequest(userId, moodType))
    }
}