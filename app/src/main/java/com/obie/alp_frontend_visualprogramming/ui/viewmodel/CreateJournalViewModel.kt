package com.obie.alp_frontend_visualprogramming.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.obie.alp_frontend_visualprogramming.data.container.JournalServerContainer
import kotlinx.coroutines.launch

class CreateJournalViewModel : ViewModel() {
    private val journalServerRepositories = JournalServerContainer().JournalServerRepository

    var isCreate by mutableStateOf(false)


    var title by mutableStateOf("")
    var date by mutableStateOf("")
    var content by mutableStateOf("")

    fun createJournal(date: String, title: String, content: String){
        viewModelScope.launch{
            try {
                journalServerRepositories.createJournal(date, title, content)
            } catch (e: Exception){

            }
        }
    }

    fun checkNullFormValues(){
        isCreate = title.isNotEmpty() && date.isNotEmpty() && content.isNotEmpty()
    }
}