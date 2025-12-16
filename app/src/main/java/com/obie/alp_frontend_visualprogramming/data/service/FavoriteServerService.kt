package com.obie.alp_frontend_visualprogramming.data.service

import com.obie.alp_frontend_visualprogramming.ui.model.*
import retrofit2.Response
import retrofit2.http.*

interface FavoriteServerService {
    @POST("api/favorites")
    suspend fun createFavorite(@Body request: CreateFavoriteRequest): Response<FavoriteResponseWrapper>

    @GET("api/favorites")
    suspend fun getAllFavorites(@Query("user_id") userId: Int): Response<FavoriteListWrapper>

    @GET("api/favorites/check")
    suspend fun checkFavorited(
        @Query("user_id") userId: Int,
        @Query("text") text: String
    ): Response<CheckFavoritedWrapper>

    @PUT("api/favorites/{id}")
    suspend fun updateFavorite(
        @Path("id") id: Int,
        @Body request: UpdateFavoriteRequest
    ): Response<FavoriteResponseWrapper>

    @DELETE("api/favorites/{id}")
    suspend fun deleteFavorite(@Path("id") id: Int): Response<DeleteFavoriteWrapper>
}