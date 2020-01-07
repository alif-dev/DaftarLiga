package com.alif.daftarliga.presenter

import com.alif.daftarliga.model.Event
import com.alif.daftarliga.model.EventResponse
import com.alif.daftarliga.model.TeamResponse
import com.alif.daftarliga.model.webservice.ApiRepository
import com.alif.daftarliga.model.webservice.TheSportDBApi
import com.alif.daftarliga.view.viewinterfaces.MatchDetailsView
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MatchDetailsPresenter(
    private val matchDetailsView: MatchDetailsView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {
    fun getMatchDetailsData(idEvent: String?, idHomeTeam: String?, idAwayTeam: String?) {
        var matchDetails: Event
        var homeTeamImage: String? = ""
        var awayTeamImage: String? = ""

        matchDetailsView.showLoading()
        doAsync {
            val matchDetailsAPIData = gson.fromJson(
                apiRepository.doRequest(TheSportDBApi.getEventDetails(idEvent)),
                EventResponse::class.java
            )
            matchDetails = matchDetailsAPIData.events[0]

            val homeTeamDetailsAPIData = gson.fromJson(
                apiRepository.doRequest(TheSportDBApi.getTeamDetails(idHomeTeam)),
                TeamResponse::class.java
            )
            homeTeamImage = homeTeamDetailsAPIData.teams[0].strTeamBadge

            val awayTeamDetailsAPIData = gson.fromJson(
                apiRepository.doRequest(TheSportDBApi.getTeamDetails(idAwayTeam)),
                TeamResponse::class.java
            )
            awayTeamImage = awayTeamDetailsAPIData.teams[0].strTeamBadge

            uiThread {
                matchDetailsView.hideLoading()
                matchDetailsView.showMatchDetails(matchDetails, homeTeamImage, awayTeamImage)
            }
        }
    }
}