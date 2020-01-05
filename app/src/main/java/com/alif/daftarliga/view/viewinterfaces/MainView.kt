package com.alif.daftarliga.view.viewinterfaces

import com.alif.daftarliga.model.EventResponse
import com.alif.daftarliga.model.League

interface MainView {
    fun showLoading()
    fun hideLoading()
    fun showLeagueImageGridList(
        leagueList: List<League>,
        allLeaguesNextMatchList: ArrayList<EventResponse>,
        allLeaguesPrevMatchList: ArrayList<EventResponse>
    )
}