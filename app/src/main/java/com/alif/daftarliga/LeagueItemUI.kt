package com.alif.daftarliga

import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.*

class LeagueItemUI : AnkoComponent<ViewGroup> {
    companion object {
        const val img_league = 1
    }

    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
        verticalLayout {
            lparams(matchParent, wrapContent)
            padding = dip(16) //test change

            imageView {
                id = img_league
            }.lparams {
                height = dip(150)
                width = dip(150)
                gravity = Gravity.CENTER //test change
            }
        }
    }
}