package com.obie.alp_frontend_visualprogramming.ui.model

data class CreateJournalRequest (
    val date: String,
    val title: String,
    val content: String,
    val user_id: Int = 1
)