package com.alif.daftarliga.presenter

import com.alif.daftarliga.model.EventResponse
import com.alif.daftarliga.model.League
import com.alif.daftarliga.model.LeagueResponse
import com.alif.daftarliga.model.webservice.ApiRepository
import com.alif.daftarliga.model.webservice.TheSportDBApi
import com.alif.daftarliga.view.viewinterfaces.MainView
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainPresenter(
    private val mainView: MainView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {

    fun getLeagueDataFromAPI(leagueIds: Array<String>) {
        val leagueDataList: MutableList<League> = mutableListOf()
        val allLeaguesNextMatchDataList: ArrayList<EventResponse> = ArrayList()
        val allLeaguesPrevMatchDataList: ArrayList<EventResponse> = ArrayList()

        mainView.showLoading()
        doAsync {
            for (i in leagueIds.indices) {
                val leagueApiData = gson.fromJson(
                    apiRepository.doRequest(TheSportDBApi.getLeagueData(leagueIds[i])),
                    LeagueResponse::class.java
                )

                val nextMatchEventsApiData = gson.fromJson(
                    apiRepository.doRequest(TheSportDBApi.getNextMatchesData(leagueIds[i])),
                    EventResponse::class.java
                )

                val previousMatchesAPIData = gson.fromJson(
                    apiRepository.doRequest(TheSportDBApi.getPreviousMatchesData(leagueIds[i])),
                    EventResponse::class.java
                )

                // data API yang diambil dan di-add ke dalam list adalah yang bernama leagues (hasil dari response API (LeagueResponse.kt))
                // dan index-nya adalah data ke-0 karena memang hasil response dari API-nya hanya 1 data (data detail dari satu Liga)
                // jadi, dengan kata lain setiap leagueId memiliki satu league (details)
                leagueDataList.add(leagueApiData.leagues[0])
                allLeaguesNextMatchDataList.add(nextMatchEventsApiData)
                allLeaguesPrevMatchDataList.add(previousMatchesAPIData)
            }

            uiThread {
                mainView.hideLoading()
                // tampilkan imageGridList sekaligus melakukan fetch semua data dari API
                println("nextmatches: " + allLeaguesNextMatchDataList[0])
                mainView.showLeagueImageGridList(leagueDataList, allLeaguesNextMatchDataList, allLeaguesPrevMatchDataList)
            }
        }
    }
}