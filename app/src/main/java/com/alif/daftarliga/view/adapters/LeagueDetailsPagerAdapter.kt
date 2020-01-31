package com.alif.daftarliga.view.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.alif.daftarliga.model.Event
import com.alif.daftarliga.model.League
import com.alif.daftarliga.model.Table
import com.alif.daftarliga.model.Team
import com.alif.daftarliga.view.*

class LeagueDetailsPagerAdapter(fm: FragmentManager,
                                private val leagueData: League?,
                                private val nextMatchList: ArrayList<Event>?,
                                private val prevMatchList: ArrayList<Event>?,
                                private val standingsList: ArrayList<Table>?,
                                private val teamList: ArrayList<Team>?): FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> LeagueDetailsFragment.newInstance(leagueData, "")
            1 -> NextMatchFragment.newInstance(nextMatchList, "")
            2 -> PreviousMatchFragment.newInstance(prevMatchList,"")
            3 -> StandingsFragment.newInstance(standingsList, "")
            else -> TeamListFragment.newInstance(teamList, "")
        }
    }

    override fun getCount(): Int {
        return 5
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Details"
            1 -> "Next Match"
            2 -> "Previous Match"
            3 -> "Standings"
            else -> "Team List"
        }
    }
}