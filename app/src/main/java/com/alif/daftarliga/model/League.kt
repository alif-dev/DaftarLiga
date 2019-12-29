package com.alif.daftarliga.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class League (
    @SerializedName("idLeague")
    var leagueId: String? = null,
    @SerializedName("idSoccerXML")
    var leagueIdSoccerXML: String? = null,
    @SerializedName("strSport")
    var leagueSportType: String? = null,
    @SerializedName("strLeague")
    var leagueName: String? = null,
    @SerializedName("strLeagueAlternate")
    var leagueAlternateName: String? = null,
    @SerializedName("strDivision")
    var leagueDivision: String? = null,
    @SerializedName("idCup")
    var leagueIdCup: String? = null,
    @SerializedName("intFormedYear")
    var leagueFormedYear: String? = null,
    @SerializedName("dateFirstEvent")
    var leagueFirstEvent: String? = null,
    @SerializedName("strGender")
    var leaguePlayerGender: String? = null,
    @SerializedName("strCountry")
    var leagueCountry: String? = null,
    @SerializedName("strWebsite")
    var leagueWebsite: String? = null,
    @SerializedName("strFacebook")
    var leagueFacebook: String? = null,
    @SerializedName("strTwitter")
    var leagueTwitter: String? = null,
    @SerializedName("strYoutube")
    var leagueYoutube: String? = null,
    @SerializedName("strRSS")
    var leagueRSSFeed: String? = null,
    @SerializedName("strDescriptionEN")
    var leagueDescription: String? = null,
    @SerializedName("strDescriptionDE")
    var leagueDescriptionDE: String? = null,
    @SerializedName("strDescriptionFR")
    var leagueDescriptionFR: String? = null,
    @SerializedName("strDescriptionIT")
    var leagueDescriptionIT: String? = null,
    @SerializedName("strDescriptionCN")
    var leagueDescriptionCN: String? = null,
    @SerializedName("strDescriptionJP")
    var leagueDescriptionJP: String? = null,
    @SerializedName("strDescriptionRU")
    var leagueDescriptionRU: String? = null,
    @SerializedName("strDescriptionES")
    var leagueDescriptionES: String? = null,
    @SerializedName("strDescriptionPT")
    var leagueDescriptionPT: String? = null,
    @SerializedName("strDescriptionSE")
    var leagueDescriptionSE: String? = null,
    @SerializedName("strDescriptionNL")
    var leagueDescriptionNL: String? = null,
    @SerializedName("strDescriptionHU")
    var leagueDescriptionHU: String? = null,
    @SerializedName("strDescriptionNO")
    var leagueDescriptionNO: String? = null,
    @SerializedName("strDescriptionPL")
    var leagueDescriptionPL: String? = null,
    @SerializedName("strDescriptionIL")
    var leagueDescriptionIL: String? = null,
    @SerializedName("strFanart1")
    var leagueFanart1: String? = null,
    @SerializedName("strFanart2")
    var leagueFanart2: String? = null,
    @SerializedName("strFanart3")
    var leagueFanart3: String? = null,
    @SerializedName("strFanart4")
    var leagueFanart4: String? = null,
    @SerializedName("strBanner")
    var leagueBanner: String? = null,
    @SerializedName("strBadge")
    var leagueBadge: String? = null,
    @SerializedName("strLogo")
    var leagueLogo: String? = null,
    @SerializedName("strPoster")
    var leaguePoster: String? = null,
    @SerializedName("strTrophy")
    var leagueTrophyImage: String? = null,
    @SerializedName("strNaming")
    var leagueNaming: String? = null,
    @SerializedName("strComplete")
    var leagueCompleteStatus: String? = null,
    @SerializedName("strLocked")
    var leagueLockedStatus: String? = null
) : Parcelable