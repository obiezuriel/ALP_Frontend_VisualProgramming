package com.obie.alp_frontend_visualprogramming.data.service

import com.obie.alp_frontend_visualprogramming.ui.model.CreateJournalRequest
import com.obie.alp_frontend_visualprogramming.ui.model.JournalDetailWrapper
import com.obie.alp_frontend_visualprogramming.ui.model.JournalWrapper
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface JournalServerService {
    @GET("api/journal")
    suspend fun getAllJournals(): Response<JournalWrapper>

    @GET("api/journal/{id}")
    suspend fun getJournalById(@Path("id") id: Int): Response<JournalDetailWrapper>

    @POST("api/journal")
    suspend fun createJournal(@Body journal: CreateJournalRequest)
}




