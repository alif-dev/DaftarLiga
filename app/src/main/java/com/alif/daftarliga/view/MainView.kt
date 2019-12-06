package com.alif.daftarliga.view

import com.alif.daftarliga.model.League

interface MainView {
    fun showLeagueImageGridList(leagueData: List<League>)
}