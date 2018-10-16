package com.example.eko8757.footballclubmatchdatabase.model

import com.google.gson.annotations.SerializedName

data class Events(

        @SerializedName("idEvent")
        var eventId: String? = null,

        @SerializedName("idHomeTeam")
        var homeTeamId: String? = null,

        @SerializedName("idAwayTeam")
        var awayTeamId: String? = null,

        @SerializedName("strEvent")
        var event: String? = null,

        @SerializedName("strHomeTeam")
        var homeTeam: String? = null,

        @SerializedName("strAwayTeam")
        var awayTeam: String? = null,

        @SerializedName("intHomeScore")
        var homeScore: String? = null,

        @SerializedName("intAwayScore")
        var awayScore: String? = null,

        @SerializedName("dateEvent")
        var dateEvent: String? = null
)