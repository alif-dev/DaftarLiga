@file:Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package com.alif.daftarliga.utilities

import android.annotation.SuppressLint
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
object DateFormatter {

    fun formatDateToLocal(dateString: String?): String {
        var date: Date? = null
        var formattedDate = "N/A"
        try {
            date = SimpleDateFormat("yyyy-MM-dd").parse(dateString)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        if (date != null) {
            val localeIndonesia = Locale("in", "ID")
            formattedDate = if (Locale.getDefault() == localeIndonesia) {
                SimpleDateFormat("EEE, d MMMM yyyy", localeIndonesia).format(date)
            } else {
                SimpleDateFormat("MMMM d, yyyy", Locale.getDefault())
                    .format(date)
            }
        }
        return formattedDate
    }

    fun formatDateToCommon(dateString: String?): String {
        var date: Date? = null
        var formattedDate = "N/A"
        try {
            date = SimpleDateFormat("yyyy-MM-dd").parse(dateString)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        if (date != null) {
            formattedDate = SimpleDateFormat("EEE, d MMMM yyyy", Locale.ENGLISH)
                .format(date)
        }
        return formattedDate
    }
}