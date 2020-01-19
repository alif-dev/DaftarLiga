package com.alif.daftarliga.utilities

import com.alif.daftarliga.utilities.DateFormatter.formatDateToCommon
import org.junit.Assert.assertEquals
import org.junit.Test

class DateFormatterTest {

    @Test
    fun testFormatDateToCommon() {
        val date = "2020-01-10"
        assertEquals("Fri, 10 Jan 2020", formatDateToCommon(date))
    }
}