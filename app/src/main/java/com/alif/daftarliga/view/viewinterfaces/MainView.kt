package com.alif.daftarliga.view.viewinterfaces

import com.alif.daftarliga.model.League

interface MainView {
    fun showLeagueImageGridList(leagueList: List<League>)
}