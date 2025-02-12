package com.alif.daftarliga.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Table(
    @SerializedName("name") val name : String? = null,
    @SerializedName("teamid") val teamId : String? = null,
    @SerializedName("played") val played : String? = null,
    @SerializedName("goalsfor") val goalsFor : String? = null,
    @SerializedName("goalsagainst") val goalsAgainst : String? = null,
    @SerializedName("goalsdifference") val goalsDifference : String? = null,
    @SerializedName("win") val win : String? = null,
    @SerializedName("draw") val draw : String? = null,
    @SerializedName("loss") val loss : String? = null,
    @SerializedName("total") val total : String? = null
) : Parcelable