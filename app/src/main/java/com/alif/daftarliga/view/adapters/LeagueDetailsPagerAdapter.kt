package com.alif.daftarliga.view.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.alif.daftarliga.model.Event
import com.alif.daftarliga.model.League
import com.alif.daftarliga.view.LeagueDetailsFragment
import com.alif.daftarliga.view.NextMatchFragment
import com.alif.daftarliga.view.PreviousMatchFragment

class LeagueDetailsPagerAdapter(fm: FragmentManager, private val leagueData: League?,
                                private val nextMatchList: ArrayList<Event>?,
                                private val prevMatchList: ArrayList<Event>?): FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> LeagueDetailsFragment.newInstance(leagueData, "")
            1 -> NextMatchFragment.newInstance(nextMatchList, "")
            else -> PreviousMatchFragment.newInstance(prevMatchList,"")
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Details"
            1 -> "Next Match"
            else -> "Previous Match"
        }
    }
}