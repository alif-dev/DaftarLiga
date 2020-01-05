package com.alif.daftarliga.presenter

import com.alif.daftarliga.model.Event
import com.alif.daftarliga.model.EventResponse
import com.alif.daftarliga.model.webservice.ApiRepository
import com.alif.daftarliga.model.webservice.TheSportDBApi
import com.alif.daftarliga.view.viewinterfaces.NextMatchesView
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class NextMatchesPresenter(
    private val nextMatchesView: NextMatchesView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {
    fun getNextMatchesDataFromAPI(leagueId: String?) {
        val nextMatchDataList: ArrayList<Event> = ArrayList()

        doAsync {
            val nextMatchesAPIData = gson.fromJson(
                apiRepository.doRequest(TheSportDBApi.getNextMatchesData(leagueId)),
                EventResponse::class.java
            )
            // lakukan iterasi untuk menambah (add) event ke dalam list sebanyak jumlah event yang didapat dari API
            // jadi, dengan kata lain setiap leagueId memiliki banyak events
            for (jsonPosition in nextMatchesAPIData.events.indices) {
                nextMatchDataList.add(nextMatchesAPIData.events[jsonPosition])
            }

            uiThread {
                nextMatchesView.passNextMatchesData(nextMatchDataList)
            }
        }
    }
}