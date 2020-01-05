package com.alif.daftarliga.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EventResponse(val events: List<Event>) : Parcelable