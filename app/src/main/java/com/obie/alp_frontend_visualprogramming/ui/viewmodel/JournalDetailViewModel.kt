package com.obie.alp_frontend_visualprogramming.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.obie.alp_frontend_visualprogramming.data.container.JournalServerContainer
import com.obie.alp_frontend_visualprogramming.ui.uistate.JournalDetailUIState
import kotlinx.coroutines.launch

class JournalDetailViewModel : ViewModel() {

    private val journalServerRepositories = JournalServerContainer().JournalServerRepository

    var journalDetailStatus: JournalDetailUIState by mutableStateOf(JournalDetailUIState.Start)
        private set

    fun getJournalById(id: Int) {
        viewModelScope.launch {
            journalDetailStatus = JournalDetailUIState.Loading
            try{
                val response = journalServerRepositories.getJournalById(id)
                if(response.isSuccessful){
                    val journal = response.body()!!.data
                    journalDetailStatus = JournalDetailUIState.Success(journal)
                }
            } catch (e: Exception){
                journalDetailStatus = JournalDetailUIState.Error(e.message.toString())
            }
        }
    }
}