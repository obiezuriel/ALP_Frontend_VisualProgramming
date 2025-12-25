package com.obie.alp_frontend_visualprogramming.data.service

import com.obie.alp_frontend_visualprogramming.ui.model.MeditationWrapper
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MeditationServerService {
    @GET("api/meditations")
    suspend fun getAllMeditations(): Response<MeditationWrapper>

    @GET("api/meditations/{id}")
    suspend fun getMeditationById(@Path("id") id: Int): Response<MeditationWrapper.MeditationData>
}

