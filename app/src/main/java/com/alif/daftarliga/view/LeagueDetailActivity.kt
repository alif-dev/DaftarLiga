package com.alif.daftarliga.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alif.daftarliga.R
import com.alif.daftarliga.model.League
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_league_detail.*

class LeagueDetailActivity : AppCompatActivity() {

    private var leagueImg: String = ""
    private var leagueName: String = ""
    private var leagueAlternateName: String = ""
    private var leagueFormedYear: String = ""
    private var leagueFirstEvent: String = ""
    private var leaguePlayerGender: String = ""
    private var leagueCountry: String = ""
    private var leagueWebsite: String = ""
    private var leagueFacebook: String = ""
    private var leagueTwitter: String = ""
    private var leagueYoutube: String = ""
    private var leagueRSSFeed: String = ""
    private var leagueDescription: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_league_detail)

        val intent = intent
        val bundle = intent.getParcelableExtra<League>("leagueData")

        leagueImg = bundle?.leagueBadge.toString()
        leagueName = bundle?.leagueName.toString()
        leagueAlternateName = bundle?.leagueAlternateName.toString()
        leagueFormedYear = bundle?.leagueFormedYear.toString()
        leagueFirstEvent = bundle?.leagueFirstEvent.toString()
        leaguePlayerGender = bundle?.leaguePlayerGender.toString()
        leagueCountry = bundle?.leagueCountry.toString()
        leagueWebsite = bundle?.leagueWebsite.toString()
        leagueFacebook = bundle?.leagueFacebook.toString()
        leagueTwitter = bundle?.leagueTwitter.toString()
        leagueYoutube = bundle?.leagueYoutube.toString()
        leagueRSSFeed = bundle?.leagueRSSFeed.toString()
        leagueDescription = bundle?.leagueDescription.toString()

        Glide.with(applicationContext).load(leagueImg).into(img_league)
        tv_league_name_big.text = leagueName
        tv_league_name.text = leagueName
        tv_league_alternate_name.text = leagueAlternateName
        tv_league_formed_year.text = leagueFormedYear
        tv_league_first_event.text = leagueFirstEvent
        tv_league_player_gender.text = leaguePlayerGender
        tv_league_country.text = leagueCountry
        tv_league_website.text = leagueWebsite
        tv_league_facebook.text = leagueFacebook
        tv_league_twitter.text = leagueTwitter
        tv_league_youtube.text = leagueYoutube
        tv_league_rss_feed.text = leagueRSSFeed
        tv_league_description.text = leagueDescription

        // enable the Up button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.title_detail_liga)
    }

    // back when the Up button is pressed
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
