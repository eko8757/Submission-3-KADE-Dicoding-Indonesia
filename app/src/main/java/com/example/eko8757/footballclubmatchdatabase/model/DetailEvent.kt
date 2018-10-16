package com.example.eko8757.footballclubmatchdatabase.model

import com.google.gson.annotations.SerializedName

data class DetailEvent(

        @SerializedName("idEvent")
        var eventId: String? = null,

        @SerializedName("strEvent")
        var event: String? = null,

        @SerializedName("strLeague")
        var league: String? = null,

        @SerializedName("strHomeTeam")
        var homeTeam: String? = null,

        @SerializedName("strAwayTeam")
        var awayTeam: String? = null,

        @SerializedName("intHomeScore")
        var homeScore: String? = null,

        @SerializedName("intAwayScore")
        var awayScore: String? = null,

        @SerializedName("idHomeTeam")
        var homeTeamId: String? = null,

        @SerializedName("idAwayTeam")
        var awayTeamId: String? = null,

        @SerializedName("strHomeGoalDetails")
        var homeGoalDetail: String? = null,

        @SerializedName("strAwayGoalDetails")
        var awayGoalDetail: String? = null,

        @SerializedName("strHomeRedCards")
        var homeRedCards: String? = null,

        @SerializedName("strHomeYellowCards")
        var homeYellowCards: String? = null,

        @SerializedName("strAwayRedCards")
        var awayRedCards: String? = null,

        @SerializedName("strAwayYellowCards")
        var awayYellowCards: String? = null,

        @SerializedName("dateEvent")
        var dateEvent: String? = null
)