package com.example.eko8757.footballclubmatchdatabase.model

import com.google.gson.annotations.SerializedName

data class Team (

        @SerializedName("idTeam")
        var teamId: String? = null,

        @SerializedName("strTeamBadge")
        var teamBadge: String? = null
)