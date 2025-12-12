package com.obie.alp_frontend_visualprogramming.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.obie.alp_frontend_visualprogramming.data.container.JournalServerContainer
import com.obie.alp_frontend_visualprogramming.ui.uistate.AllJournalingUIState
import kotlinx.coroutines.launch

class AllJournalingViewModel : ViewModel() {
    private val journalServerRepositories = JournalServerContainer().JournalServerRepository

    var allJournalingStatus: AllJournalingUIState by mutableStateOf(AllJournalingUIState.Start)
        private set

    init {
        getAllJournals()
    }

    fun getAllJournals(){
        viewModelScope.launch{
            allJournalingStatus = AllJournalingUIState.Loading
            try {
                val response = journalServerRepositories.getAllJournals()
                if(response.isSuccessful){
                    val journals = response.body()!!.data
                    allJournalingStatus = AllJournalingUIState.Success(journals)
                }
            } catch(e: Exception){
                allJournalingStatus = AllJournalingUIState.Error(e.message.toString())
            }
        }
    }
}