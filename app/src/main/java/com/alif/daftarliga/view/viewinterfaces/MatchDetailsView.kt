package com.alif.daftarliga.view.viewinterfaces

import com.alif.daftarliga.model.Event

interface MatchDetailsView {
    fun showLoading()
    fun hideLoading()
    fun showMatchDetails(matchDetails: Event, homeTeamImage: String?, awayTeamImage: String?)
}