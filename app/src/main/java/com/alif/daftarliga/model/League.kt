package com.alif.daftarliga.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class League (
    @SerializedName("idLeague")
    var leagueId: String? = null,
    @SerializedName("strLeague")
    var leagueName: String? = null,
    @SerializedName("strBadge")
    var leagueImage: String? = null,
    @SerializedName("strDescriptionEN")
    var leagueDescription: String? = null
) : Parcelable