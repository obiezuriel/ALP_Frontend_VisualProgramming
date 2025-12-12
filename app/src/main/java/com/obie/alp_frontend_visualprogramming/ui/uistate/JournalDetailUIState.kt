package com.obie.alp_frontend_visualprogramming.ui.uistate

import com.obie.alp_frontend_visualprogramming.ui.model.JournalDetailResponse
import com.obie.alp_frontend_visualprogramming.ui.model.JournalResponse

sealed interface JournalDetailUIState {
    data class Success(val journal: JournalDetailResponse) : JournalDetailUIState
    data class Error(val message: String) : JournalDetailUIState
    object Loading : JournalDetailUIState
    object Start: JournalDetailUIState
}