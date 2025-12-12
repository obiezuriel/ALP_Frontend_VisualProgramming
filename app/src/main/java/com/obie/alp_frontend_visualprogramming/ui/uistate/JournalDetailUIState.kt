package com.obie.alp_frontend_visualprogramming.ui.uistate

import com.obie.alp_frontend_visualprogramming.ui.model.JournalDetailResponse


sealed interface JournalDetailUIState {
    data class Success(val journal: JournalDetailResponse) : JournalDetailUIState
    data class Error(val message: String) : JournalDetailUIState
    object Loading : JournalDetailUIState
    object Start: JournalDetailUIState
}