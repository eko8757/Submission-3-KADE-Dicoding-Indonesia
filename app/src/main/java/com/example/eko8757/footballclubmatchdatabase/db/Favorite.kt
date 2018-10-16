package com.example.eko8757.footballclubmatchdatabase.db

class Favorite(val id: Long?, val eventId: String?, val homeTeamId: String?, val homeTeam: String?,
               val awayTeamId: String?, val awayTeam: String?, val homeScore: String?, val awayScore: String?,
               val dateEvent: String?) {

    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val EVENT_ID = "EVENT_ID"
        const val HOMETEAM_ID = "HOMETEAM_ID"
        const val HOMETEAM = "HOMETEAM"
        const val AWAYTEAM_ID = "AWAYTEAM_ID"
        const val AWAYTEAM = "AWAYTEAM"
        const val HOMESCORE = "HOMESCORE"
        const val AWAYSCORE = "AWAYSCORE"
        const val DATEEVENT = "DATEEVENT"
    }
}