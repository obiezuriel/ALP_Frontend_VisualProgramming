package com.obie.alp_frontend_visualprogramming.ui.model

data class MeditationWrapper(
    val status: String,
    val data: List<MeditationData>
){
    data class MeditationData(
        val id: Int,
        val title: String,
        val duration: Int,
        val description: String,
        val audioUrl: String,
        val coverImage: String
    )
}
