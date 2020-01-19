package com.alif.daftarliga.presenter

import com.alif.daftarliga.model.Event
import com.alif.daftarliga.model.EventResponse
import com.alif.daftarliga.model.TeamResponse
import com.alif.daftarliga.model.webservice.ApiRepository
import com.alif.daftarliga.model.webservice.TheSportDBApi
import com.alif.daftarliga.utilities.CoroutineContextProvider
import com.alif.daftarliga.view.viewinterfaces.MatchDetailsView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MatchDetailsPresenter(
    private val matchDetailsView: MatchDetailsView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getMatchDetailsData(idEvent: String?, idHomeTeam: String?, idAwayTeam: String?) {
        var matchDetails: Event
        var homeTeamImage: String?
        var awayTeamImage: String?

        matchDetailsView.showLoading()
        GlobalScope.launch(context.main) {
            val matchDetailsAPIData = gson.fromJson(
                apiRepository.doRequestWithCoroutinesAsync(TheSportDBApi.getEventDetails(idEvent)).await(),
                EventResponse::class.java
            )
            matchDetails = matchDetailsAPIData.events[0]

            val homeTeamDetailsAPIData = gson.fromJson(
                apiRepository.doRequestWithCoroutinesAsync(TheSportDBApi.getTeamDetails(idHomeTeam)).await(),
                TeamResponse::class.java
            )
            homeTeamImage = homeTeamDetailsAPIData.teams[0].strTeamBadge

            val awayTeamDetailsAPIData = gson.fromJson(
                apiRepository.doRequestWithCoroutinesAsync(TheSportDBApi.getTeamDetails(idAwayTeam)).await(),
                TeamResponse::class.java
            )
            awayTeamImage = awayTeamDetailsAPIData.teams[0].strTeamBadge

            matchDetailsView.hideLoading()
            matchDetailsView.showMatchDetails(matchDetails, homeTeamImage, awayTeamImage)
        }
    }
}