package com.obie.alp_frontend_visualprogramming.ui.model
data class FavoriteData(
    val id: Int,
    val user_id: Int,
    val affirmation_text: String,
    val note: String?,
    val created_at: String
)