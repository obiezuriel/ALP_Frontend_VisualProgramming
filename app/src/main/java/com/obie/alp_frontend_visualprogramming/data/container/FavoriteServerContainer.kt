package com.obie.alp_frontend_visualprogramming.data.container

import com.google.gson.GsonBuilder
import com.obie.alp_frontend_visualprogramming.data.repository.FavoriteServerRepository
import com.obie.alp_frontend_visualprogramming.data.service.FavoriteServerService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FavoriteServerContainer {
    companion object {
        val BASE_URL = "http://10.0.2.2:3000/"
    }

    private val client = OkHttpClient.Builder()
        .build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .baseUrl(BASE_URL)
        .client(client)
        .build()

    private val favoriteRetrofitService: FavoriteServerService by lazy {
        retrofit.create(FavoriteServerService::class.java)
    }

    val favoriteRepository: FavoriteServerRepository by lazy {
        FavoriteServerRepository(favoriteRetrofitService)
    }
}