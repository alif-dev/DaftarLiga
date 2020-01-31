package com.alif.daftarliga.model

class FavoriteTeam(
    val id: Long,
    val idTeam: String,
    val strTeam: String,
    val strCountry: String,
    val strTeamBadge: String? = null
) {
    companion object {
        const val TABLE_FAVORITE_TEAM = "TABLE_FAVORITE_TEAM"
        const val ID = "ID_"
        const val ID_TEAM = "ID_TEAM"
        const val TEAM_NAME = "TEAM_NAME"
        const val TEAM_COUNTRY = "TEAM_COUNTRY"
        const val TEAM_BADGE = "TEAM_BADGE"
    }
}