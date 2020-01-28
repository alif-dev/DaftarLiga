package com.alif.daftarliga.view.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.alif.daftarliga.view.FavoriteFragment
import com.alif.daftarliga.view.LeagueListFragment
import com.alif.daftarliga.view.SearchMatchFragment
import com.alif.daftarliga.view.SearchTeamFragment

class SearchPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> SearchMatchFragment.newInstance("", "")
            else -> SearchTeamFragment.newInstance("", "")
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Search Match"
            else -> "Search Team"
        }
    }
}