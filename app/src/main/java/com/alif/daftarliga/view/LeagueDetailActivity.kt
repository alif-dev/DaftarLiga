package com.alif.daftarliga.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alif.daftarliga.R
import com.alif.daftarliga.model.*
import com.alif.daftarliga.view.adapters.LeagueDetailsPagerAdapter
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_league_details.*

@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class LeagueDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_league_details)

        // enable the Up button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.title_detail_liga)

        val intent = intent
        val leagueData = intent.getParcelableExtra<League>(MainActivity.LEAGUE_DATA_KEY)
        val nextMatches =
            intent.getParcelableExtra<EventResponse>(MainActivity.NEXT_EVENTS_DATA_KEY)
        val prevMatches =
            intent.getParcelableExtra<EventResponse>(MainActivity.PREV_EVENTS_DATA_KEY)
        val standings = intent.getParcelableExtra<TableResponse>(MainActivity.STANDINGS_DATA_KEY)
        val teams = intent.getParcelableExtra<TeamResponse>(MainActivity.TEAMS_DATA_KEY)

        // put EventResponse data into ArrayList<Event> (nextMatchList)
        var nextMatchList: ArrayList<Event>? = ArrayList()
        if (!nextMatches?.events.isNullOrEmpty()) {
            for (element in nextMatches.events) {
                nextMatchList?.add(element)
                // println("test: $element") // show data in Logcat
            }
        } else {
            nextMatchList = null
        }

        // put EventResponse data into ArrayList<Event> (prevMatchList)
        var prevMatchList: ArrayList<Event>? = ArrayList()
        if (!prevMatches?.events.isNullOrEmpty()) {
            for (element in prevMatches.events) {
                prevMatchList?.add(element)
                // println("test: $element") // show data in Logcat
            }
        } else {
            prevMatchList = null
        }

        // put TableResponse data into ArrayList<Table> (standingsList)
        var standingsList: ArrayList<Table>? = ArrayList()
        if (!standings?.table.isNullOrEmpty()) {
            for (table in standings.table) {
                standingsList?.add(table)
                // println("test: $table") // show data in Logcat
            }
        } else {
            standingsList = null
        }

        // put TeamResponse data into ArrayList<Team> (teamList)
        var teamList: ArrayList<Team>? = ArrayList()
        if (!teams?.teams.isNullOrEmpty()) {
            for (team in teams.teams) {
                teamList?.add(team)
            }
        } else {
            teamList = null
        }

        Glide.with(applicationContext).load(leagueData?.leagueBadge).into(img_league_detail)
        tv_league_name_big.text = leagueData?.leagueName

        viewpager_league_details.adapter = LeagueDetailsPagerAdapter(
            supportFragmentManager,
            leagueData,
            nextMatchList,
            prevMatchList,
            standingsList,
            teamList
        )
        tabs_league_details.setupWithViewPager(viewpager_league_details)
    }

    // back when the Up button is pressed
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
