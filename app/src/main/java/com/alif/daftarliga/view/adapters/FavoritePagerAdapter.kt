package com.alif.daftarliga.view.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.alif.daftarliga.view.*

class FavoritePagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> FavoriteNextMatchesFragment.newInstance("", "")
            1 -> FavoritePreviousMatchesFragment.newInstance("", "")
            else -> FavoriteTeamsFragment.newInstance("", "")
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Next Matches"
            1 -> "Prev Matches"
            else -> "Teams"
        }
    }
}