package com.alif.daftarliga.presenter

import com.alif.daftarliga.model.Team
import com.alif.daftarliga.model.TeamResponse
import com.alif.daftarliga.model.webservice.ApiRepository
import com.alif.daftarliga.model.webservice.TheSportDBApi
import com.alif.daftarliga.utilities.CoroutineContextProvider
import com.alif.daftarliga.view.viewinterfaces.TeamDetailsView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeamDetailsPresenter(
    private val teamDetailsView: TeamDetailsView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getTeamDetailsData(idTeam: String?) {
        var teamDetails: Team

        teamDetailsView.showLoading()
        GlobalScope.launch(context.main) {
            val teamDetailsAPIData = gson.fromJson(
                apiRepository.doRequestWithCoroutinesAsync(TheSportDBApi.getTeamDetails(idTeam)).await(),
                TeamResponse::class.java
            )
            teamDetails = teamDetailsAPIData.teams[0]

            teamDetailsView.hideLoading()
            teamDetailsView.showTeamDetails(teamDetails)
        }
    }
}