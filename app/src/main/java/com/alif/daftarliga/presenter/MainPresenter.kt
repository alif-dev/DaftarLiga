package com.alif.daftarliga.presenter

import com.alif.daftarliga.model.League
import com.alif.daftarliga.view.MainView
import com.alif.daftarliga.model.webservice.ApiRepository
import com.alif.daftarliga.model.webservice.TheSportDBApi
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainPresenter(
    private val view: MainView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {

    fun getLeagueDataFromAPI(leagueIds: List<String>) {
        doAsync {
            var leagueImgs: IntArray? = null
            var leagueDataList: MutableList<League> = mutableListOf()
            for (i in leagueIds.indices) {
                val data = gson.fromJson(
                    apiRepository.doRequest(TheSportDBApi.getLeagueData(leagueIds[i])),
                    League::class.java
                )
                leagueDataList.add(data)
            }

            uiThread {

                view.showLeagueImageGridList(leagueDataList)
            }
        }
    }
}