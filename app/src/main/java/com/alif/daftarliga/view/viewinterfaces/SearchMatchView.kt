package com.alif.daftarliga.view.viewinterfaces

import com.alif.daftarliga.model.EventResponse2

interface SearchMatchView {
    fun showLoading()
    fun hideLoading()
    fun showSearchedMatches(searchedMatchList: ArrayList<EventResponse2>)
}