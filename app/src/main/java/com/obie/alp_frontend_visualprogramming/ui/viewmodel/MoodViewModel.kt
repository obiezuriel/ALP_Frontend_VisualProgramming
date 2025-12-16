package com.obie.alp_frontend_visualprogramming.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.obie.alp_frontend_visualprogramming.data.container.JournalServerContainer
import com.obie.alp_frontend_visualprogramming.ui.uistate.MoodUIState
import kotlinx.coroutines.launch

class MoodViewModel : ViewModel() {
    private val moodRepository = JournalServerContainer().moodRepository

    var moodUIState: MoodUIState by mutableStateOf(MoodUIState.Start)
        private set

    fun submitMood(userId: Int, moodType: String) {
        viewModelScope.launch {
            moodUIState = MoodUIState.Loading
            try {
                val response = moodRepository.createMood(userId, moodType)
                if (response.isSuccessful) {
                    val mood = response.body()!!.data
                    moodUIState = MoodUIState.Success(mood)
                } else {
                    moodUIState = MoodUIState.Error("Gagal: ${response.code()}")
                }
            } catch (e: Exception) {
                moodUIState = MoodUIState.Error(e.message.toString())
            }
        }
    }

    fun resetState() {
        moodUIState = MoodUIState.Start
    }
}