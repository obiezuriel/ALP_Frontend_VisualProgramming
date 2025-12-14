package com.obie.alp_frontend_visualprogramming.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.obie.alp_frontend_visualprogramming.data.repository.MoodRepository
import com.obie.alp_frontend_visualprogramming.ui.uistate.MoodUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MoodViewModel(private val repository: MoodRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<MoodUIState>(MoodUIState.Start)
    val uiState: StateFlow<MoodUIState> = _uiState.asStateFlow()

    fun submitMood(moodType: String, userId: Int = 1) {
        _uiState.value = MoodUIState.Loading

        viewModelScope.launch {
            try {
                val response = repository.createMood(userId, moodType)

                if (response.isSuccessful) {

                    val wrapper = response.body()
                    val text = wrapper?.data?.affirmationText ?: "Semangat!"

                    _uiState.value = MoodUIState.Success(text)

                } else {
                    _uiState.value = MoodUIState.Error("Gagal: ${response.code()}")
                }
            } catch (e: Exception) {
                _uiState.value = MoodUIState.Error("Error: ${e.message}")
            }
        }
    }

    fun resetState() {
        _uiState.value = MoodUIState.Start
    }
}