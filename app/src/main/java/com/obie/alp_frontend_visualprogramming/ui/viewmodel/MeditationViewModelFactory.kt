package com.obie.alp_frontend_visualprogramming.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.obie.alp_frontend_visualprogramming.data.repository.MeditationServerRepository

class MeditationViewModelFactory(private val repository: MeditationServerRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MeditationViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MeditationViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
