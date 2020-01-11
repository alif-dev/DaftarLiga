package com.alif.daftarliga.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alif.daftarliga.R
import com.alif.daftarliga.view.adapters.MainPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        const val LEAGUE_DATA_KEY = "leagueData"
        const val NEXT_EVENTS_DATA_KEY = "nextEventsData"
        const val PREV_EVENTS_DATA_KEY = "prevEventsData"
        const val ID_HOME_TEAM_KEY = "idHomeTeamData"
        const val ID_AWAY_TEAM_KEY = "idAwayTeamData"
        const val ID_EVENT_KEY = "idEventData"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_view_pager.adapter = MainPagerAdapter(supportFragmentManager)
        main_view_pager.offscreenPageLimit = 5
        main_tabs.setupWithViewPager(main_view_pager)
        main_tabs.getTabAt(0)?.setIcon(R.drawable.sports_soccer_white_24dp)
        main_tabs.getTabAt(1)?.setIcon(R.drawable.ic_star_white_24dp)
        main_tabs.getTabAt(2)?.setIcon(R.drawable.ic_search_white_24dp)
    }
}
