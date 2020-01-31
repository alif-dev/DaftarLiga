package com.alif.daftarliga.view.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.alif.daftarliga.model.Team
import com.alif.daftarliga.view.TeamDetailsFragment

class TeamDetailsPagerAdapter(fm: FragmentManager, private val teamData: Team): FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return TeamDetailsFragment.newInstance(teamData, "")
    }

    override fun getCount(): Int {
        return 1
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return "Team Details"
    }
}