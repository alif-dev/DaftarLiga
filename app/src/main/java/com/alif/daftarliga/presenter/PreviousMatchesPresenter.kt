package com.alif.daftarliga.presenter

import com.alif.daftarliga.model.Event
import com.alif.daftarliga.model.EventResponse
import com.alif.daftarliga.model.webservice.ApiRepository
import com.alif.daftarliga.model.webservice.TheSportDBApi
import com.alif.daftarliga.view.viewinterfaces.PreviousMatchesView
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class PreviousMatchesPresenter(
    private val previousMatchesView: PreviousMatchesView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {
    fun getPreviousMatchesDataFromAPI(leagueId: String?) {
        val previousMatchDataList: ArrayList<Event> = ArrayList()

        doAsync {
            val previousMatchesAPIData = gson.fromJson(
                apiRepository.doRequest(TheSportDBApi.getPreviousMatchesData(leagueId)),
                EventResponse::class.java
            )
            // lakukan iterasi untuk menambah (add) event ke dalam list sebanyak jumlah event yang didapat dari API
            // jadi, dengan kata lain setiap leagueId memiliki banyak events
            for (jsonPosition in previousMatchesAPIData.events.indices) {
                previousMatchDataList.add(previousMatchesAPIData.events[jsonPosition])
            }

            uiThread {
                previousMatchesView.passPreviousMatchesData(previousMatchDataList)
            }
        }
    }
}