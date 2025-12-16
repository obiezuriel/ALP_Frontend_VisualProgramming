package com.obie.alp_frontend_visualprogramming.ui.model

data class MeditationSong(
    val id: Int,
    val title: String,
    val duration: Int,
    val description: String,
    val audioUrl: String,
    val coverImage: String
)
