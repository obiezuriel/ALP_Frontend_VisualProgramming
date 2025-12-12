package com.obie.alp_frontend_visualprogramming.data.repository

import com.obie.alp_frontend_visualprogramming.data.service.JournalServerService
import com.obie.alp_frontend_visualprogramming.ui.model.CreateJournalRequest
import com.obie.alp_frontend_visualprogramming.ui.model.JournalDetailWrapper
import com.obie.alp_frontend_visualprogramming.ui.model.JournalWrapper
import retrofit2.Response


class JournalServerRepositories(private val service: JournalServerService) {
    suspend fun getAllJournals() : Response<JournalWrapper>{
        return service.getAllJournals()
    }

    suspend fun getJournalById(id: Int) : Response<JournalDetailWrapper> {
        return service.getJournalById(id)
    }

    suspend fun createJournal(date: String, title: String, content: String){
        return service.createJournal(CreateJournalRequest(date, title, content))
    }
}