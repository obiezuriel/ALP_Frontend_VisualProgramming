package com.obie.alp_frontend_visualprogramming.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.obie.alp_frontend_visualprogramming.data.container.JournalServerContainer
import com.obie.alp_frontend_visualprogramming.ui.uistate.JournalDetailUIState

class JournalDetailViewModel : ViewModel() {

    private val journalServerRepositories = JournalServerContainer().JournalServerRepository

    var journalDetailStatus: JournalDetailUIState by mutableStateOf(JournalDetailUIState.Start)
        private set


}