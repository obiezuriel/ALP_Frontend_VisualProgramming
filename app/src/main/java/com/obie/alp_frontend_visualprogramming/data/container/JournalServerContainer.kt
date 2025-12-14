package com.obie.alp_frontend_visualprogramming.data.container

import com.google.gson.GsonBuilder
import com.obie.alp_frontend_visualprogramming.data.repository.JournalServerRepositories
import com.obie.alp_frontend_visualprogramming.data.service.JournalServerService
import com.obie.alp_frontend_visualprogramming.data.repository.MoodRepository
import com.obie.alp_frontend_visualprogramming.data.service.MoodService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class JournalServerContainer {
    companion object{
        val BASE_URL = "http://10.0.2.2:3000/"
    }

    private val client = OkHttpClient.Builder()
        .build()

    private val retrofit =  Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .baseUrl(BASE_URL)
        .client(client)
        .build()

    private val retrofitService: JournalServerService by lazy{
        retrofit.create(JournalServerService::class.java)
    }

    val JournalServerRepository : JournalServerRepositories by lazy {
        JournalServerRepositories(retrofitService)
    }

    private val moodRetrofitService: MoodService by lazy {
        retrofit.create(MoodService::class.java)
    }

    val moodRepository: MoodRepository by lazy {
        MoodRepository(moodRetrofitService)
    }
}