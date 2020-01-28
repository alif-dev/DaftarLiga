package com.alif.daftarliga.model.webservice

import com.alif.daftarliga.BuildConfig

object TheSportDBApi {

    // Detail liga
    // API endpoint: https://www.thesportsdb.com/api/v1/json/1/lookupleague.php?id={leagueId}
    fun getLeagueDetails(leagueId: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/lookupleague.php?id=" + leagueId
    }

    // Daftar pertandingan selanjutnya dari satu liga
    // API endpoint: https://www.thesportsdb.com/api/v1/json/1/eventsnextleague.php?id={leagueId}
    fun getLeagueNextMatchesData(leagueId: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/eventsnextleague.php?id=" + leagueId
    }

    // Daftar pertandingan sebelumnya dari satu liga
    // API endpoint: https://www.thesportsdb.com/api/v1/json/1/eventspastleague.php?id={leagueId}
    fun getLeaguePreviousMatchesData(leagueId: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/eventspastleague.php?id=" + leagueId
    }

    // Detail pertandingan
    // API endpoint: https://www.thesportsdb.com/api/v1/json/1/lookupevent.php?id={eventId}
    fun getEventDetails(eventId: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/lookupevent.php?id=" + eventId
    }

    // Pencarian pertandingan
    // API endpoint: https://www.thesportsdb.com/api/v1/json/1/searchevents.php?e={query}
    fun searchMatch(query: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/searchevents.php?e=" + query
    }

    // Daftar tim dalam satu liga
    fun getLeagueTeams(leagueId: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/search_all_teams.php?id=" + leagueId
    }

    // Detail tim
    // https://www.thesportsdb.com/api/v1/json/1/lookupteam.php?id={teamId}
    fun getTeamDetails(teamId: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/lookupteam.php?id=" + teamId
    }

    // Klasemen pertandingan dari satu liga
    // https://www.thesportsdb.com/api/v1/json/1/lookuptable.php?l={idLeague}
    fun getLeagueStandings(leagueId: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/lookuptable.php?id=" + leagueId
    }

    // Daftar pemain dalam satu tim
    // https://www.thesportsdb.com/api/v1/json/1/lookup_all_players.php?id={idTeam}
    fun getTeamPlayer(teamId: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/lookup_all_players.php?id=" + teamId
    }

    // Detail pemain
    // https://www.thesportsdb.com/api/v1/json/1/lookupplayer.php?id={playerId}
    fun getPlayerDetails(playerId: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/lookupplayer.php?id=" + playerId
    }

    // Daftar pertandingan selanjutnya dari satu tim
    // API endpoint: https://www.thesportsdb.com/api/v1/json/1/eventsnext.php?id={teamId}
    fun getTeamNextMatchesData(teamId: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/eventsnext.php?id=" + teamId
    }

    // Daftar pertandingan sebelumnya dari satu tim
    // API endpoint: https://www.thesportsdb.com/api/v1/json/1/eventslast.php?id={teamId}
    fun getTeamLastMatchesData(teamId: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/eventslast.php?id=" + teamId
    }

    // Pencarian tim
    // https://www.thesportsdb.com/api/v1/json/1/searchteams.php?t={query}
    fun searchTeam(query: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/searchteams.php?t=" + query
    }

    // Cara Lain Build URL
    /*// Daftar tim dalam satu liga
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
        println("uriString = $uriString") // show uri in Logcat
        return uriString
    }*/
}