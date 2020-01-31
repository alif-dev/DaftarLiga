package com.alif.daftarliga.presenter

import com.alif.daftarliga.model.*
import com.alif.daftarliga.model.webservice.ApiRepository
import com.alif.daftarliga.model.webservice.TheSportDBApi
import com.alif.daftarliga.utilities.CoroutineContextProvider
import com.alif.daftarliga.view.viewinterfaces.MainView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainPresenter(
    private val mainView: MainView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getLeagueDataFromAPI(leagueIds: Array<String>) {
        val leagueDataList: MutableList<League> = mutableListOf()
        val allLeaguesNextMatchDataList: ArrayList<EventResponse> = ArrayList()
        val allLeaguesPrevMatchDataList: ArrayList<EventResponse> = ArrayList()
        val allLeaguesStandingsDataList: ArrayList<TableResponse> = ArrayList()
        val allLeaguesTeamDataList: ArrayList<TeamResponse> = ArrayList()

        mainView.showLoading()
        GlobalScope.launch(context.main) {
            for (i in leagueIds.indices) {
                val leagueAPIData = gson.fromJson(
                    apiRepository.doRequestWithCoroutinesAsync(TheSportDBApi.getLeagueDetails(leagueIds[i])).await(),
                    LeagueResponse::class.java
                )

                val nextMatchEventsAPIData = gson.fromJson(
                    apiRepository.doRequestWithCoroutinesAsync(TheSportDBApi.getLeagueNextMatchesData(leagueIds[i])).await(),
                    EventResponse::class.java
                )

                val previousMatchesAPIData = gson.fromJson(
                    apiRepository.doRequestWithCoroutinesAsync(TheSportDBApi.getLeaguePreviousMatchesData(leagueIds[i])).await(),
                    EventResponse::class.java
                )

                val standingsAPIData = gson.fromJson(
                    apiRepository.doRequestWithCoroutinesAsync(TheSportDBApi.getLeagueStandings(leagueIds[i])).await(),
                    TableResponse::class.java
                )

                val teamsAPIData = gson.fromJson(
                    apiRepository.doRequestWithCoroutinesAsync(TheSportDBApi.getLeagueTeams(leagueIds[i])).await(),
                    TeamResponse::class.java
                )

                // data API yang diambil dan di-add ke dalam list adalah yang bernama leagues (hasil dari response API (LeagueResponse.kt))
                // dan index-nya adalah data ke-0 karena memang hasil response dari API-nya hanya 1 data (data detail dari satu Liga)
                // jadi, dengan kata lain setiap leagueId memiliki satu league (details)
                leagueDataList.add(leagueAPIData.leagues[0])
                allLeaguesNextMatchDataList.add(nextMatchEventsAPIData)
                allLeaguesPrevMatchDataList.add(previousMatchesAPIData)
                allLeaguesStandingsDataList.add(standingsAPIData)
                allLeaguesTeamDataList.add(teamsAPIData)
            }

            mainView.hideLoading()
            // tampilkan imageGridList sekaligus melakukan fetch semua data dari API
            // println("nextmatches: " + allLeaguesNextMatchDataList[0])
            mainView.showLeagueImageGridList(
                leagueDataList,
                allLeaguesNextMatchDataList,
                allLeaguesPrevMatchDataList,
                allLeaguesStandingsDataList,
                allLeaguesTeamDataList)
        }
    }
}