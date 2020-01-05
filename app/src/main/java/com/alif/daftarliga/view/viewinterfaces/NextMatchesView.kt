package com.alif.daftarliga.view.viewinterfaces

import com.alif.daftarliga.model.Event

interface NextMatchesView {
    fun passNextMatchesData(nextMatches: ArrayList<Event>)
}