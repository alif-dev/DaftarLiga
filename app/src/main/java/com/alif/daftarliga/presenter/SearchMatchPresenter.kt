package com.alif.daftarliga.presenter

import com.alif.daftarliga.model.EventResponse2
import com.alif.daftarliga.model.webservice.ApiRepository
import com.alif.daftarliga.model.webservice.TheSportDBApi
import com.alif.daftarliga.utilities.CoroutineContextProvider
import com.alif.daftarliga.view.viewinterfaces.SearchMatchView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchMatchPresenter(
    private val searchMatchView: SearchMatchView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun searchMatchDataFromAPI(query: String?) {
        val searchedMatchDataList: ArrayList<EventResponse2> = ArrayList()

        searchMatchView.showLoading()
        // using Kotlin Coroutines
        GlobalScope.launch(context.main) {
            val searchedMatchAPIData = gson.fromJson(
                apiRepository.doRequestWithCoroutinesAsync(TheSportDBApi.searchMatch(query)).await(),
                EventResponse2::class.java
            )
            // println("searchedMatches: " + searchedMatchAPIData.event[0].strHomeTeam) // show fetched API data in Logcat
            searchedMatchDataList.add(searchedMatchAPIData)

            searchMatchView.hideLoading()
            searchMatchView.showSearchedMatches(searchedMatchDataList)
        }
    }
}