package com.alif.daftarliga

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MainActivity : AppCompatActivity() {
    private var leagues: MutableList<League> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        initData()

        MainActivityUI().setContentView(this)

//        league_list.layoutManager = GridLayoutManager(this, 2)
//        league_list.adapter = DaftarLigaAdapter(this, items) {
//            val toast = Toast.makeText(applicationContext, it.name, Toast.LENGTH_SHORT)
//            toast.show()
//        }
    }

    private fun initData() {
        val leagueName = resources.getStringArray(R.array.league_name)
        val leagueImage = resources.obtainTypedArray(R.array.league_image)
        val leagueDescription = resources.getStringArray(R.array.league_description)

        leagues.clear()

        for (i in leagueName.indices) {
            leagues.add(League(leagueName[i], leagueImage.getResourceId(i, 0), leagueDescription[i]))
        }

        // recycle the typed array
        leagueImage.recycle()
    }

    class MainActivityUI : AnkoComponent<MainActivity> {
        override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
            verticalLayout {
                lparams(matchParent, wrapContent)
                recyclerView {
                    layoutManager = GridLayoutManager(context, 2)
                    adapter = LeagueListAdapter(context, leagues) {
                        startActivity<LeagueDetailActivity>("leagueData" to it)
                    }
                }
            }
        }
    }
}
