package com.alif.daftarliga.model.webservice

import android.net.Uri
import com.alif.daftarliga.BuildConfig

object TheSportDBApi {
    fun getTeams(league: String?): String {
        val uriString = BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" +
                "/search_all_teams.php?l=" + Uri.encode(league)
        return uriString
    }

    fun getTeams2(league: String?): String {
        val uriString = Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("search_all_teams.php")
            .appendQueryParameter("l", league)
            .build()
            .toString()
        return uriString
    }

    fun getLeagueData(leagueId: String?): String {
        val uriString = Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("lookupleague.php")
            .appendQueryParameter("id", leagueId)
            .build()
            .toString()
        println("uriString = $uriString")
        return uriString
    }
}