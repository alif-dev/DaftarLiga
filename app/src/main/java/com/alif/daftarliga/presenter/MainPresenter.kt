package com.alif.daftarliga.presenter

import com.alif.daftarliga.model.League
import com.alif.daftarliga.model.LeagueResponse
import com.alif.daftarliga.model.webservice.ApiRepository
import com.alif.daftarliga.model.webservice.TheSportDBApi
import com.alif.daftarliga.view.viewinterfaces.MainView
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainPresenter(
    private val view: MainView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {

    fun getLeagueDataFromAPI(leagueIds: Array<String>) {
        val leagueDataList: MutableList<League> = mutableListOf()
        doAsync {
            for (i in leagueIds.indices) {
                val data = gson.fromJson(
                    apiRepository.doRequest(TheSportDBApi.getLeagueData(leagueIds[i])),
                    LeagueResponse::class.java
                )
                leagueDataList.add(data.leagues[0])
            }

            uiThread {
                view.showLeagueImageGridList(leagueDataList)
            }
        }
    }
}