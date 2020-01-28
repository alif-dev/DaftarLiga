package com.alif.daftarliga.model

class FavoritePreviousMatch(
    val id: Long,
    val idEvent: String,
    val idHomeTeam: String,
    val idAwayTeam: String,
    val dateEvent: String,
    val strTime: String,
    val strHomeTeam: String,
    val strAwayTeam: String,
    val intHomeScore: String,
    val intAwayScore: String
) {
    companion object {
        const val TABLE_FAVORITE_PREV_MATCH = "TABLE_FAVORITE_PREV_MATCH"
        const val ID = "ID_"
        const val ID_EVENT = "ID_EVENT"
        const val ID_HOME_TEAM = "ID_HOME_TEAM"
        const val ID_AWAY_TEAM = "ID_AWAY_TEAM"
        const val DATE_EVENT = "DATE_EVENT"
        const val TIME = "TIME"
        const val HOME_TEAM = "HOME_TEAM"
        const val AWAY_TEAM = "AWAY_TEAM"
        const val HOME_SCORE = "HOME_SCORE"
        const val AWAY_SCORE = "AWAY_SCORE"
    }
}