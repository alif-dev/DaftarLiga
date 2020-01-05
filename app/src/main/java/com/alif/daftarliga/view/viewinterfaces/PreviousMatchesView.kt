package com.alif.daftarliga.view.viewinterfaces

import com.alif.daftarliga.model.Event

interface PreviousMatchesView {
    fun passPreviousMatchesData(previousMatches: ArrayList<Event>)
}