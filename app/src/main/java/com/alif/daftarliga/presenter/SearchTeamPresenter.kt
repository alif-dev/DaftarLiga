package com.alif.daftarliga.presenter

import com.alif.daftarliga.model.TeamResponse
import com.alif.daftarliga.model.webservice.ApiRepository
import com.alif.daftarliga.model.webservice.TheSportDBApi
import com.alif.daftarliga.utilities.CoroutineContextProvider
import com.alif.daftarliga.view.viewinterfaces.SearchTeamView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchTeamPresenter(
    private val searchTeamView: SearchTeamView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun searchTeamDataFromAPI(query: String?) {
        val searchedTeamDataList: ArrayList<TeamResponse> = ArrayList()

        searchTeamView.showLoading()
        // using Kotlin Coroutines
        GlobalScope.launch(context.main) {
            val searchedTeamAPIData = gson.fromJson(
                apiRepository.doRequestWithCoroutinesAsync(TheSportDBApi.searchTeam(query)).await(),
                TeamResponse::class.java
            )
            // println("searchedTeams: " + searchedTeamAPIData.teams[0].strTeam) // show fetched API data in Logcat
            searchedTeamDataList.add(searchedTeamAPIData)

            searchTeamView.hideLoading()
            searchTeamView.showSearchedTeams(searchedTeamDataList)
        }
    }
}