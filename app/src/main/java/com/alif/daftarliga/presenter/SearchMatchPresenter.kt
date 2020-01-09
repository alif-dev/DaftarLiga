package com.alif.daftarliga.presenter

import com.alif.daftarliga.model.EventResponse2
import com.alif.daftarliga.model.webservice.ApiRepository
import com.alif.daftarliga.model.webservice.TheSportDBApi
import com.alif.daftarliga.view.viewinterfaces.SearchMatchView
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class SearchMatchPresenter(
    private val searchMatchView: SearchMatchView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {
    fun searchMatchDataFromAPI(query: String?) {
        val searchedMatchDataList: ArrayList<EventResponse2> = ArrayList()

        searchMatchView.showLoading()
        doAsync {
            val searchedMatchAPIData = gson.fromJson(
                apiRepository.doRequest(TheSportDBApi.searchMatch(query)),
                EventResponse2::class.java
            )
            // println("searchedMaches: " + searchedMatchAPIData.event[0].strHomeTeam) // show fetched API data in Logcat
            searchedMatchDataList.add(searchedMatchAPIData)

            uiThread {
                searchMatchView.hideLoading()
                searchMatchView.showSearchedMatches(searchedMatchDataList)
            }
        }
    }
}