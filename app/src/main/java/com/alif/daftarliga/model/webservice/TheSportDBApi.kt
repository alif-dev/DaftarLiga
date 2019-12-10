package com.alif.daftarliga.model.webservice

import android.net.Uri
import com.alif.daftarliga.BuildConfig

object TheSportDBApi {

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
        println("uriString = $uriString") // show data in Logcat
        return uriString
    }
}