package com.alif.daftarliga.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alif.daftarliga.R
import com.alif.daftarliga.model.League
import com.alif.daftarliga.view.adapters.LeagueDetailsPagerAdapter
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_league_details.*

class LeagueDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_league_details)

        val intent = intent
        val leagueData = intent.getParcelableExtra<League>(MainActivity.LEAGUE_DATA_KEY)
        
        Glide.with(applicationContext).load(leagueData?.leagueBadge).into(img_league)
        tv_league_name_big.text = leagueData?.leagueName

        viewpager_league_details.adapter = LeagueDetailsPagerAdapter(supportFragmentManager, leagueData)
        tabs_league_details.setupWithViewPager(viewpager_league_details)

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
