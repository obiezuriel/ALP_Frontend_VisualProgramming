package com.obie.alp_frontend_visualprogramming.data.repository

import com.obie.alp_frontend_visualprogramming.data.service.MeditationServerService
import com.obie.alp_frontend_visualprogramming.ui.model.MeditationWrapper
import retrofit2.Response

class MeditationServerRepository(private val service: MeditationServerService) {
    suspend fun getAllMeditations(): Response<MeditationWrapper> {
        return service.getAllMeditations()
    }

    suspend fun getMeditationById(id: Int): Response<MeditationWrapper.MeditationData> {
        return service.getMeditationById(id)
    }
}
