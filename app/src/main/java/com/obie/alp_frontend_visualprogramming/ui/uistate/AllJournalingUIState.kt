package com.obie.alp_frontend_visualprogramming.ui.uistate

import com.obie.alp_frontend_visualprogramming.ui.model.JournalResponse

sealed interface AllJournalingUIState {
    data class Success(val journals: List<JournalResponse>) : AllJournalingUIState
    data class Error(val message: String) : AllJournalingUIState
    object Loading : AllJournalingUIState
    object Start: AllJournalingUIState
}