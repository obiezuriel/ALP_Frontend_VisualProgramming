package com.obie.alp_frontend_visualprogramming.data.repository

import com.obie.alp_frontend_visualprogramming.data.service.FavoriteServerService
import com.obie.alp_frontend_visualprogramming.ui.model.*
import retrofit2.Response

class FavoriteServerRepository(private val service: FavoriteServerService) {
    suspend fun createFavorite(userId: Int, affirmationText: String): Response<FavoriteResponseWrapper> {
        return service.createFavorite(CreateFavoriteRequest(userId, affirmationText))
    }

    suspend fun getAllFavorites(userId: Int): Response<FavoriteListWrapper> {
        return service.getAllFavorites(userId)
    }

    suspend fun checkFavorited(userId: Int, text: String): Response<CheckFavoritedWrapper> {
        return service.checkFavorited(userId, text)
    }

    suspend fun updateFavorite(id: Int, note: String): Response<FavoriteResponseWrapper> {
        return service.updateFavorite(id, UpdateFavoriteRequest(note))
    }

    suspend fun deleteFavorite(id: Int): Response<DeleteFavoriteWrapper> {
        return service.deleteFavorite(id)
    }
}