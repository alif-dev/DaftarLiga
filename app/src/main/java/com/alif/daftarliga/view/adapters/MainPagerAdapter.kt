package com.alif.daftarliga.view.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.alif.daftarliga.view.FavoriteFragment
import com.alif.daftarliga.view.LeagueListFragment
import com.alif.daftarliga.view.SearchMatchFragment

class MainPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> LeagueListFragment.newInstance("", "")
            1 -> FavoriteFragment.newInstance("", "")
            else -> SearchMatchFragment.newInstance("", "")
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Leagues"
            1 -> "Favorites"
            else -> "Search"
        }
    }
}