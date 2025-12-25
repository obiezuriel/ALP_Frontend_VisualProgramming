package com.obie.alp_frontend_visualprogramming.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.obie.alp_frontend_visualprogramming.data.container.MeditationServerContainer
import com.obie.alp_frontend_visualprogramming.ui.uistate.MeditationUIState
import kotlinx.coroutines.launch
import android.util.Log

class MeditationViewModel : ViewModel() {
    private val meditationRepository = MeditationServerContainer().meditationServerRepository

    var meditationState: MeditationUIState by mutableStateOf(MeditationUIState.Start)
        private set

    init {
        getAllMeditations()
    }

    fun getAllMeditations(){
        viewModelScope.launch{
            meditationState = MeditationUIState.Loading
            try {
                val response = meditationRepository.getAllMeditations()
                if(response.isSuccessful){
                    val body = response.body()
                    if(body != null){
                        val meditations = body.data
                        meditationState = MeditationUIState.Success(meditations)
                        Log.d("MeditationVM", "Success: ${meditations.size} items loaded")
                    } else {
                        meditationState = MeditationUIState.Error("Response body is null")
                        Log.e("MeditationVM", "Response body is null")
                    }
                } else {
                    meditationState = MeditationUIState.Error("Error: ${response.code()} - ${response.message()}")
                    Log.e("MeditationVM", "Failed: ${response.code()} - ${response.message()}")
                }
            } catch(e: Exception){
                meditationState = MeditationUIState.Error(e.message ?: "Unknown error")
                Log.e("MeditationVM", "Exception: ${e.message}", e)
            }
        }
    }
}

