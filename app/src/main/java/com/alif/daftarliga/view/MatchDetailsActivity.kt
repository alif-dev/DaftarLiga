package com.alif.daftarliga.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.alif.daftarliga.R
import com.alif.daftarliga.model.Event
import com.alif.daftarliga.model.FavoriteMatch
import com.alif.daftarliga.model.database.dbNextMatches
import com.alif.daftarliga.model.database.dbPrevMatches
import com.alif.daftarliga.model.webservice.ApiRepository
import com.alif.daftarliga.presenter.MatchDetailsPresenter
import com.alif.daftarliga.utilities.DateFormatter
import com.alif.daftarliga.view.viewinterfaces.MatchDetailsView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_match_details.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast

class MatchDetailsActivity : AppCompatActivity(), MatchDetailsView {
    private lateinit var matchDetailsPresenter: MatchDetailsPresenter
    private var idEvent: String? = null
    private var idHomeTeam: String? = null
    private var idAwayTeam: String? = null
    private var dateEvent: String? = null
    private var time: String? = null
    private var homeTeam: String? = null
    private var awayTeam: String? = null
    private var homeScore: String? = null
    private var awayScore: String? = null
    private var eventData: String? = null
    private var favoriteState = false
    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_details)

        // enable the Up button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.title_match_details)

        val intent = intent
        idEvent = intent.getStringExtra(MainActivity.ID_EVENT_KEY)
        idHomeTeam = intent.getStringExtra(MainActivity.ID_HOME_TEAM_KEY)
        idAwayTeam = intent.getStringExtra(MainActivity.ID_AWAY_TEAM_KEY)
        eventData = intent.getStringExtra(MainActivity.EVENT_DATA_KEY)

        initData(idEvent, idHomeTeam, idAwayTeam)
    }

    private fun initData(idEvent: String?, idHomeTeam: String?, idAwayTeam: String?) {
        val apiRepository = ApiRepository()
        val gson = Gson()

        matchDetailsPresenter = MatchDetailsPresenter(this, apiRepository, gson)
        matchDetailsPresenter.getMatchDetailsData(idEvent, idHomeTeam, idAwayTeam)
    }

    override fun showLoading() {
        container_match_details.visibility = View.INVISIBLE
        pb_match_details.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        pb_match_details.visibility = View.GONE
        container_match_details.visibility = View.VISIBLE
    }

    override fun showMatchDetails(
        matchDetails: Event,
        homeTeamImage: String?,
        awayTeamImage: String?
    ) {

        homeTeam = matchDetails.strHomeTeam
        awayTeam = matchDetails.strAwayTeam
        dateEvent = DateFormatter.formatDateToCommon(matchDetails.dateEvent)
        time = matchDetails.strTime

        Glide.with(this).load(homeTeamImage).into(home_team_image)
        Glide.with(this).load(awayTeamImage).into(away_team_image)
        home_team_name.text = matchDetails.strHomeTeam
        away_team_name.text = matchDetails.strAwayTeam
        tv_match_date.text = DateFormatter.formatDateToCommon(matchDetails.dateEvent)
        tv_match_time.text = matchDetails.strTime

        if (!matchDetails.intHomeScore.isNullOrBlank()) {
            homeScore = matchDetails.intHomeScore
            home_team_score.text = matchDetails.intHomeScore
            tv_home_score.text = matchDetails.intHomeScore
        } else {
            homeScore = ""
            home_team_score.text = "-"
            tv_home_score.text = "-"
        }
        if (!matchDetails.intAwayScore.isNullOrBlank()) {
            awayScore = matchDetails.intAwayScore
            away_team_score.text = matchDetails.intAwayScore
            tv_away_score.text = matchDetails.intAwayScore
        } else {
            awayScore = ""
            away_team_score.text = "-"
            tv_away_score.text = "-"
        }

        tv_dtl_home_team_name.text = matchDetails.strHomeTeam
        tv_dtl_away_team_name.text = matchDetails.strAwayTeam
        tv_home_round.text = matchDetails.intRound
        tv_away_round.text = matchDetails.intRound
        if (!matchDetails.strDescriptionEN.isNullOrBlank()) tv_match_description.text =
            matchDetails.strDescriptionEN
        else tv_match_description.text = getString(R.string.no_description)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        this.menu = menu
        menuInflater.inflate(R.menu.menu_favorite, menu)
        // check whether the match data is already in FavoriteMatch database or not
        // and then change the favorite icon based on that condition
        checkFavoriteStateAndChangeIcon()
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_favorite_match -> {
                if (!favoriteState) addAsFavorite() else removeFromFavorite()

                // jika data telah ditambahkan ke daftar favorit, maka berarti favoritState yang awalnya false harus diubah menjadi true
                // dan sebaliknya jika data telah dihapus dari daftar favorit maka favoriteState yang awalnya true harus diubah menjadi false
                favoriteState = !favoriteState

                // ubah icon favorit sesuai dengan favoriteState yang sekarang
                changeFavoriteIcon(favoriteState)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun changeFavoriteIcon(favoriteState: Boolean) {
        if (favoriteState) menu?.getItem(0)?.icon =
            ContextCompat.getDrawable(this, R.drawable.ic_star_white_24dp)
        else menu?.getItem(0)?.icon =
            ContextCompat.getDrawable(this, R.drawable.ic_star_border_white_24dp)
    }

    private fun addAsFavorite() {
        if (eventData.equals(MainActivity.PREV_EVENT_DATA_VALUE)) {
            dbPrevMatches.use {
                insert(
                    FavoriteMatch.TABLE_FAVORITE_MATCH,
                    FavoriteMatch.ID_EVENT to idEvent,
                    FavoriteMatch.ID_HOME_TEAM to idHomeTeam,
                    FavoriteMatch.ID_AWAY_TEAM to idAwayTeam,
                    FavoriteMatch.DATE_EVENT to dateEvent,
                    FavoriteMatch.TIME to time,
                    FavoriteMatch.HOME_TEAM to homeTeam,
                    FavoriteMatch.AWAY_TEAM to awayTeam,
                    FavoriteMatch.HOME_SCORE to homeScore,
                    FavoriteMatch.AWAY_SCORE to awayScore
                )
            }
        } else {
            dbNextMatches.use {
                insert(
                    FavoriteMatch.TABLE_FAVORITE_MATCH,
                    FavoriteMatch.ID_EVENT to idEvent,
                    FavoriteMatch.ID_HOME_TEAM to idHomeTeam,
                    FavoriteMatch.ID_AWAY_TEAM to idAwayTeam,
                    FavoriteMatch.DATE_EVENT to dateEvent,
                    FavoriteMatch.TIME to time,
                    FavoriteMatch.HOME_TEAM to homeTeam,
                    FavoriteMatch.AWAY_TEAM to awayTeam,
                    FavoriteMatch.HOME_SCORE to homeScore,
                    FavoriteMatch.AWAY_SCORE to awayScore
                )
            }
        }
        toast(getString(R.string.toast_added_match_as_favorite))
    }

    private fun checkFavoriteStateAndChangeIcon() {
        if (eventData.equals(MainActivity.PREV_EVENT_DATA_VALUE)) {
            dbPrevMatches.use {
                val result = select(FavoriteMatch.TABLE_FAVORITE_MATCH)
                    .whereArgs(
                        "(ID_EVENT = {idEvent})",
                        "idEvent" to idEvent.toString()
                    )
                val favoriteMatch = result.parseList(classParser<FavoriteMatch>())
                if (favoriteMatch.isNotEmpty()) favoriteState = true

                // change favoriteIcon based on favoriteState
                changeFavoriteIcon(favoriteState)
            }
        } else {
            dbNextMatches.use {
                val result = select(FavoriteMatch.TABLE_FAVORITE_MATCH)
                    .whereArgs(
                        "(ID_EVENT = {idEvent})",
                        "idEvent" to idEvent.toString()
                    )
                val favoriteMatch = result.parseList(classParser<FavoriteMatch>())
                if (favoriteMatch.isNotEmpty()) favoriteState = true

                // change favoriteIcon based on favoriteState
                changeFavoriteIcon(favoriteState)
            }
        }
    }

    private fun removeFromFavorite() {
        if (eventData.equals(MainActivity.PREV_EVENT_DATA_VALUE)) {
            dbPrevMatches.use {
                delete(
                    FavoriteMatch.TABLE_FAVORITE_MATCH,
                    "(ID_EVENT = {idEvent})",
                    "idEvent" to idEvent.toString()
                )
            }
        } else {
            dbNextMatches.use {
                delete(
                    FavoriteMatch.TABLE_FAVORITE_MATCH,
                    "(ID_EVENT = {idEvent})",
                    "idEvent" to idEvent.toString()
                )
            }
        }
        toast(R.string.toast_deleted_match_from_favorite)
    }

    // back when the Up button is pressed
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
