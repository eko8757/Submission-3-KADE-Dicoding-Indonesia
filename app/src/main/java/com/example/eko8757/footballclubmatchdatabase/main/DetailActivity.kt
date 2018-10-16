package com.example.eko8757.footballclubmatchdatabase.main

import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.example.eko8757.footballclubmatchdatabase.R.id.add_favorite
import com.example.eko8757.footballclubmatchdatabase.R.menu.favorite_menu
import com.example.eko8757.footballclubmatchdatabase.db.Favorite
import com.example.eko8757.footballclubmatchdatabase.db.database
import com.example.eko8757.footballclubmatchdatabase.R
import com.example.eko8757.footballclubmatchdatabase.api.ApiRepository
import com.example.eko8757.footballclubmatchdatabase.model.DetailEvent
import com.example.eko8757.footballclubmatchdatabase.model.Team
import com.example.eko8757.footballclubmatchdatabase.presenter.DetailPresenter
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast

class DetailActivity : AppCompatActivity(), DetailView {

    private var idEvent: String = ""
    private var idHomeTeam: String = ""
    private var idAwayTeam: String = ""
    private lateinit var presenter: DetailPresenter
    private lateinit var events: DetailEvent

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    override fun getDetailEvent(data: DetailEvent) {
        events = DetailEvent(data.eventId, data.event, data.league, data.homeTeam, data.awayTeam,
                data.homeScore, data.awayScore, data.homeTeamId, data.awayTeamId, data.homeGoalDetail, data.awayGoalDetail,
                data.homeRedCards, data.awayRedCards, data.homeYellowCards, data.awayYellowCards, data.dateEvent)
        idHomeTeam = data.homeTeamId!!
        idAwayTeam = data.awayTeamId.toString()
        when (data.eventId) {
            idEvent -> {

                eventName.text = data.event
                leagueName.text = data.homeTeam
                homeTeam.text = data.homeTeam
                awayTeam.text = data.awayTeam
                homeScore.text = data.homeScore
                awayScore.text = data.awayScore
                goalHomeDetail.text = data.homeGoalDetail
                goalAwayDetail.text = data.awayGoalDetail
                homeRedCardDetail.text = data.homeRedCards
                awayRedCardDetail.text = data.awayRedCards
                homeYellowCardDetail.text = data.homeYellowCards
                awayYellowCardDetail.text = data.awayYellowCards
                vs.text = "VS"
            }
        }

        presenter.getTeam(idHomeTeam)
        presenter.getTeam(idAwayTeam)
    }

    override fun showTeam(data: Team) {
        Log.d("idHome2", idHomeTeam)
        when (data.teamId) {
            idHomeTeam -> {
                Picasso.get().load(data.teamBadge).into(homeBadge)
            }
            idAwayTeam -> {
                Picasso.get().load(data.teamBadge).into(awayBadge)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(favorite_menu, menu)
        menuItem = menu
        favoriteState()
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }

            add_favorite -> {
                if (isFavorite) {
                    removeFromFavorite()
                } else {
                    addFavorite()
                }

                isFavorite = !isFavorite
                setFavorite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addFavorite() {
        val TAG: String = "TAG1"
        try {
            database.use {
                insert(Favorite.TABLE_FAVORITE,
                        Favorite.EVENT_ID to events.eventId,
                        Favorite.HOMETEAM_ID to events.homeTeamId,
                        Favorite.HOMETEAM to events.homeTeam,
                        Favorite.AWAYTEAM_ID to events.awayTeamId,
                        Favorite.AWAYTEAM to events.awayTeam,
                        Favorite.HOMESCORE to events.homeScore,
                        Favorite.AWAYSCORE to events.awayScore,
                        Favorite.DATEEVENT to events.dateEvent)
            }

            toast("Inserted to Favorite")

            Log.d(TAG, events.homeTeam)
            Log.d(TAG, Favorite.HOMETEAM)
            Log.d(TAG, Favorite.AWAYTEAM)
            Log.d(TAG, idHomeTeam)
            Log.d(TAG, events.homeTeamId)
            Log.d(TAG, "home team id")
        } catch (e: SQLiteConstraintException) {
            toast(e.localizedMessage)
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(Favorite.TABLE_FAVORITE, "(EVENT_ID = {eventId})", "eventId" to idEvent)

            }
            toast("Favorite is deleted")
        } catch (e: SQLiteConstraintException) {
            toast(e.localizedMessage)
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.baseline_star_rate_black_24dp)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.baseline_stars_black_24dp)
    }

    private fun favoriteState() {
        database.use {
            val result = select(Favorite.TABLE_FAVORITE).whereArgs("(EVENT_ID = {id})", "id" to idEvent)
            val favorite = result.parseList(classParser<Favorite>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setSupportActionBar(toolbar)

        supportActionBar?.title = "Event Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val intent = intent
        idEvent = intent.getStringExtra("idEvent")

        val request = ApiRepository()
        val gson = Gson()
        presenter = DetailPresenter(this, request, gson)

        presenter.getDetailEvent(idEvent)
    }
}
