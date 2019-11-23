package com.alif.daftarliga

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.alif.daftarliga.R.array.*
import com.alif.daftarliga.R.color.mainBackground
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MainActivity : AppCompatActivity() {
    private var leagues: MutableList<League> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initData()

        verticalLayout {
            lparams(matchParent, wrapContent)
            backgroundColor = ContextCompat.getColor(context, mainBackground)

            recyclerView {
                layoutManager = GridLayoutManager(context, 2)
                adapter = LeagueListAdapter(context, leagues) {
                    startActivity<LeagueDetailActivity>("leagueData" to it)
                }
            }
        }
    }

    private fun initData() {
        val leagueName = resources.getStringArray(league_name)
        val leagueImage = resources.obtainTypedArray(league_image)
        val leagueDescription = resources.getStringArray(league_desc)

        leagues.clear()

        for (i in leagueName.indices) {
            leagues.add(League(leagueName[i], leagueImage.getResourceId(i, 0), leagueDescription[i]))
        }

        // recycle the typed array
        leagueImage.recycle()
    }
}
