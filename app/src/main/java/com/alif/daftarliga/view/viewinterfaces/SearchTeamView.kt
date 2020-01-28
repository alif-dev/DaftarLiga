package com.alif.daftarliga.view.viewinterfaces

import com.alif.daftarliga.model.TeamResponse

interface SearchTeamView {
    fun showLoading()
    fun hideLoading()
    fun showSearchedTeams(searchedTeamList: ArrayList<TeamResponse>)
}