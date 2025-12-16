package com.obie.alp_frontend_visualprogramming.ui.uistate

import com.obie.alp_frontend_visualprogramming.ui.model.FavoriteData

sealed interface AllFavoriteUIState {
    data class Success(val favorites: List<FavoriteData>) : AllFavoriteUIState
    data class Error(val message: String) : AllFavoriteUIState
    object Loading : AllFavoriteUIState
    object Start : AllFavoriteUIState
}