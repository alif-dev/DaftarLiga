package com.alif.daftarliga.model.webservice

import android.net.Uri
import com.alif.daftarliga.BuildConfig

object TheSportDBApi {

    // API endpoint: https://www.thesportsdb.com/api/v1/json/1/lookupleague.php?id={leagueId}
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
        // println("uriString = $uriString") // show uri in Logcat
        return uriString
    }

    // API endpoint: https://www.thesportsdb.com/api/v1/json/1/eventsnextleague.php?id={leagueId}
    fun getNextMatchesData(leagueId: String?): String {
        val uriString = Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("eventsnextleague.php")
            .appendQueryParameter("id", leagueId)
            .build()
            .toString()
         // println("uriString = $uriString") // show uri in Logcat
        return uriString
    }

    // API endpoint: https://www.thesportsdb.com/api/v1/json/1/eventspastleague.php?id={leagueId}
    fun getPreviousMatchesData(leagueId: String?): String {
        val uriString = Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("eventspastleague.php")
            .appendQueryParameter("id", leagueId)
            .build()
            .toString()
        // println("uriString = $uriString") // show uri in Logcat
        return uriString
    }

    // API endpoint: https://www.thesportsdb.com/api/v1/json/1/lookupevent.php?id={eventId}
    fun getEventDetails(eventId: String?): String {
        val uriString = Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("lookupevent.php")
            .appendQueryParameter("id", eventId)
            .build()
            .toString()
        // println("uriString = $uriString") // show uri in Logcat
        return uriString
    }

    // API endpoint: https://www.thesportsdb.com/api/v1/json/1/searchevents.php?e={eventQuery}
    fun searchMatch(eventQuery: String?): String {
        val uriString = Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("searchevents.php")
            .appendQueryParameter("e", eventQuery)
            .build()
            .toString()
         // println("uriString = $uriString") // show uri in Logcat
        return uriString
    }

    // get all teams in a league
    fun getLeagueTeams(leagueId: String?): String {
        val uriString = Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("search_all_teams.php")
            .appendQueryParameter("id", leagueId)
            .build()
            .toString()
        // println("uriString = $uriString") // show uri in Logcat
        return uriString
    }

    // https://www.thesportsdb.com/api/v1/json/1/lookupteam.php?id={teamId}
    fun getTeamDetails(teamId: String?): String {
        val uriString = Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("lookupteam.php")
            .appendQueryParameter("id", teamId)
            .build()
            .toString()
        // println("uriString = $uriString") // show uri in Logcat
        return uriString
    }
}