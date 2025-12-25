package com.obie.alp_frontend_visualprogramming.ui.uistate

import com.obie.alp_frontend_visualprogramming.ui.model.MeditationWrapper

sealed class MeditationUIState {
    object Start : MeditationUIState()
    object Loading : MeditationUIState()
    data class Success(val meditations: List<MeditationWrapper.MeditationData>) : MeditationUIState()
    data class Error(val message: String) : MeditationUIState()
}
