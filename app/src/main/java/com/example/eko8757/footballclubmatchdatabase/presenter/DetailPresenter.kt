package com.example.eko8757.footballclubmatchdatabase.presenter

import com.example.eko8757.footballclubmatchdatabase.api.ApiRepository
import com.example.eko8757.footballclubmatchdatabase.api.TheSportDBApi
import com.example.eko8757.footballclubmatchdatabase.main.DetailView
import com.example.eko8757.footballclubmatchdatabase.model.DetailEventResponse
import com.example.eko8757.footballclubmatchdatabase.model.TeamResponse
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class DetailPresenter(private val view: DetailView,
                      private val apiRepository: ApiRepository,
                      private val gson: Gson) {

    fun getTeam(idTeam: String?) {
        doAsync {
            val data = gson.fromJson(apiRepository.doRequest(TheSportDBApi.getTeam(idTeam)),
                    TeamResponse::class.java)

            uiThread {
                view.showTeam(data.teams.first())
            }
        }
    }

    fun getDetailEvent(idEvent: String?) {
        doAsync {
            val data = gson.fromJson(apiRepository.doRequest(TheSportDBApi.getDetailEvent(idEvent)),
                    DetailEventResponse::class.java)

            uiThread {
                view.getDetailEvent(data.events.first())
            }
        }
    }
}