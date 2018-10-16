package com.example.eko8757.footballclubmatchdatabase.main

import com.example.eko8757.footballclubmatchdatabase.model.DetailEvent
import com.example.eko8757.footballclubmatchdatabase.model.Team

interface DetailView {

    fun showTeam(data: Team)
    fun getDetailEvent(data: DetailEvent)
}