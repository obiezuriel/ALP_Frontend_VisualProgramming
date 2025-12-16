package com.obie.alp_frontend_visualprogramming.ui.uistate

import com.obie.alp_frontend_visualprogramming.ui.model.MoodData

sealed interface MoodUIState {
    data class Success(val mood: MoodData) : MoodUIState
    data class Error(val message: String) : MoodUIState
    object Loading : MoodUIState
    object Start : MoodUIState
}