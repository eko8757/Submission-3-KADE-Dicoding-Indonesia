package com.example.eko8757.footballclubmatchdatabase.main

import com.example.eko8757.footballclubmatchdatabase.model.Events

interface MatchView {

    fun showLoading()
    fun hideLoading()
    fun showPrevEvent(data: List<Events>)
    fun showNextEvent(data: List<Events>)
}