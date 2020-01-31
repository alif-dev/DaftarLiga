package com.alif.daftarliga.view.viewinterfaces

import com.alif.daftarliga.model.Team

interface TeamDetailsView {
    fun showLoading()
    fun hideLoading()
    fun showTeamDetails(teamDetails: Team)
}