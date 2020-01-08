package com.alif.daftarliga.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.alif.daftarliga.R
import com.alif.daftarliga.model.Event
import com.alif.daftarliga.model.webservice.ApiRepository
import com.alif.daftarliga.presenter.MatchDetailsPresenter
import com.alif.daftarliga.utilities.DateFormatter
import com.alif.daftarliga.view.viewinterfaces.MatchDetailsView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_match_details.*

class MatchDetailsActivity : AppCompatActivity(), MatchDetailsView {
    private lateinit var matchDetailsPresenter: MatchDetailsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_details)

        // enable the Up button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.title_match_details)

        val intent = intent
        val idEvent = intent.getStringExtra(MainActivity.ID_EVENT_KEY)
        val idHomeTeam = intent.getStringExtra(MainActivity.ID_HOME_TEAM_KEY)
        val idAwayTeam = intent.getStringExtra(MainActivity.ID_AWAY_TEAM_KEY)

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
        Glide.with(this).load(homeTeamImage).into(home_team_image)
        Glide.with(this).load(awayTeamImage).into(away_team_image)
        home_team_name.text = matchDetails.strHomeTeam
        away_team_name.text = matchDetails.strAwayTeam
        tv_match_date.text = DateFormatter.formatDateToCommon(matchDetails.dateEvent)
        tv_match_time.text = matchDetails.strTime

        if (!matchDetails.intHomeScore.isNullOrBlank()) {
            home_team_score.text = matchDetails.intHomeScore
            tv_home_score.text = matchDetails.intHomeScore
        } else {
            home_team_score.text = "-"
            tv_home_score.text = "-"
        }
        if (!matchDetails.intAwayScore.isNullOrBlank()) {
            away_team_score.text = matchDetails.intAwayScore
            tv_away_score.text = matchDetails.intAwayScore
        } else {
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

    // back when the Up button is pressed
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
