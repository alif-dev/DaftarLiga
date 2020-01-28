@file:Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package com.alif.daftarliga.utilities

import android.annotation.SuppressLint
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
object DateFormatter {

    fun formatDateToCommon(dateString: String?): String {
        var date: Date? = null
        var formattedDate = "N/A"
        try {
            date = SimpleDateFormat("yyyy-MM-dd").parse(dateString)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        if (date != null) {
            formattedDate = SimpleDateFormat("EEE, d MMM yyyy", Locale.ENGLISH)
                .format(date)
        }
        return formattedDate
    }
}