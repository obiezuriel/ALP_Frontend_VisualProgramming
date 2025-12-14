package com.obie.alp_frontend_visualprogramming.ui.uistate

sealed interface MoodUIState {
    data class Success(val affirmation: String) : MoodUIState
    data class Error(val message: String) : MoodUIState
    object Loading : MoodUIState
    object Start : MoodUIState
}