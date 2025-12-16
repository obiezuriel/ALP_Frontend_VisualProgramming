package com.obie.alp_frontend_visualprogramming.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.obie.alp_frontend_visualprogramming.data.container.JournalServerContainer
import com.obie.alp_frontend_visualprogramming.ui.uistate.AllFavoriteUIState
import kotlinx.coroutines.launch

class FavoriteViewModel : ViewModel() {
    private val favoriteRepository = JournalServerContainer().favoriteRepository

    var allFavoriteUIState: AllFavoriteUIState by mutableStateOf(AllFavoriteUIState.Start)
        private set

    var isFavorited: Boolean by mutableStateOf(false)
        private set

    //GET
    fun getAllFavorites(userId: Int) {
        viewModelScope.launch {
            allFavoriteUIState = AllFavoriteUIState.Loading
            try {
                val response = favoriteRepository.getAllFavorites(userId)
                if (response.isSuccessful) {
                    val favorites = response.body()!!.data
                    allFavoriteUIState = AllFavoriteUIState.Success(favorites)
                } else {
                    allFavoriteUIState = AllFavoriteUIState.Error("Gagal: ${response.code()}")
                }
            } catch (e: Exception) {
                allFavoriteUIState = AllFavoriteUIState.Error(e.message.toString())
            }
        }
    }

    //POST
    fun createFavorite(userId: Int, affirmationText: String) {
        viewModelScope.launch {
            try {
                val response = favoriteRepository.createFavorite(userId, affirmationText)
                if (response.isSuccessful) {
                    isFavorited = true
                    // Refresh list
                    getAllFavorites(userId)
                }
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    //GET cek kata afirmasi udah di favorit
    fun checkFavorited(userId: Int, text: String) {
        viewModelScope.launch {
            try {
                val response = favoriteRepository.checkFavorited(userId, text)
                if (response.isSuccessful) {
                    isFavorited = response.body()!!.data.is_favorited
                }
            } catch (e: Exception) {
                isFavorited = false
            }
        }
    }

    //PUT
    fun updateFavorite(id: Int, note: String, userId: Int) {
        viewModelScope.launch {
            try {
                val response = favoriteRepository.updateFavorite(id, note)
                if (response.isSuccessful) {
                    // Refresh list
                    getAllFavorites(userId)
                }
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    //DELETE
    fun deleteFavorite(id: Int, userId: Int) {
        viewModelScope.launch {
            try {
                val response = favoriteRepository.deleteFavorite(id)
                if (response.isSuccessful) {
                    isFavorited = false
                    // Refresh list
                    getAllFavorites(userId)
                }
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    fun resetFavoriteState() {
        isFavorited = false
    }
}