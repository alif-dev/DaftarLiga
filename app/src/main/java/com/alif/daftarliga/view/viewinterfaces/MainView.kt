package com.alif.daftarliga.view.viewinterfaces

import com.alif.daftarliga.model.EventResponse
import com.alif.daftarliga.model.League
import com.alif.daftarliga.model.TableResponse
import com.alif.daftarliga.model.TeamResponse

interface MainView {
    fun showLoading()
    fun hideLoading()
    fun showLeagueImageGridList(
        leagueList: List<League>,
        allLeaguesNextMatchList: ArrayList<EventResponse>,
        allLeaguesPrevMatchList: ArrayList<EventResponse>,
        allLeaguesStandingsDataList: ArrayList<TableResponse>,
        allLeaguesTeamDataList: ArrayList<TeamResponse>
    )
}