package com.example.eko8757.footballclubmatchdatabase.presenter

import com.example.eko8757.footballclubmatchdatabase.api.ApiRepository
import com.example.eko8757.footballclubmatchdatabase.api.TheSportDBApi
import com.example.eko8757.footballclubmatchdatabase.main.MatchView
import com.example.eko8757.footballclubmatchdatabase.model.EventsResponse
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MatchPresenter(private val view: MatchView,
                     private val apiRepository: ApiRepository,
                     private val gson: Gson) {

    fun getPrevEventsList() {
        view.showLoading()
        doAsync {
            var data = gson.fromJson(apiRepository.doRequest(TheSportDBApi.getPrevEvents()),
                    EventsResponse::class.java)

            uiThread {
                view.hideLoading()
                view.showPrevEvent(data.events)
            }
        }
    }

    fun getNextEventsList() {
        view.showLoading()
        doAsync {
            var data = gson.fromJson(apiRepository.doRequest(TheSportDBApi.getNextEvents()),
                    EventsResponse::class.java)

            uiThread {
                view.hideLoading()
                view.showNextEvent(data.events)
            }
        }
    }
}