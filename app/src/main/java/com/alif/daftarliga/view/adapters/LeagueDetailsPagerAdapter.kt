package com.alif.daftarliga.view.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.alif.daftarliga.model.Event
import com.alif.daftarliga.model.League
import com.alif.daftarliga.view.LeagueDetailsFragment
import com.alif.daftarliga.view.NextMatchFragment
import com.alif.daftarliga.view.PreviousMatchFragment
import com.alif.daftarliga.view.SearchMatchFragment

class LeagueDetailsPagerAdapter(fm: FragmentManager, private val leagueData: League?,
                                private val nextMatchList: ArrayList<Event>?,
                                private val prevMatchList: ArrayList<Event>?): FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> LeagueDetailsFragment.newInstance(leagueData, "")
            1 -> NextMatchFragment.newInstance(nextMatchList, "")
            2 -> PreviousMatchFragment.newInstance(prevMatchList,"")
            else -> SearchMatchFragment.newInstance("", "")
        }
    }

    override fun getCount(): Int {
        return 4
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Details"
            1 -> "Next Match"
            2 -> "Previous Match"
            else -> "Search Match"
        }
    }
}