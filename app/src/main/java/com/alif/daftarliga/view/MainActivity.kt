package com.alif.daftarliga.view

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alif.daftarliga.model.League
import com.alif.daftarliga.R.array.*
import com.alif.daftarliga.R.color.mainBackground
import com.alif.daftarliga.model.LeagueResponse
import com.alif.daftarliga.model.webservice.ApiRepository
import com.alif.daftarliga.model.webservice.TheSportDBApi
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MainActivity : AppCompatActivity() {
    private var leagueDataList: MutableList<League> = mutableListOf()
    private lateinit var rvLeagueList: RecyclerView
    private lateinit var mainAdapter: LeagueListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initData()

        verticalLayout {
            lparams(matchParent, wrapContent)
            backgroundColor = ContextCompat.getColor(context, mainBackground)

            rvLeagueList = recyclerView {
                layoutManager = GridLayoutManager(context, 2)
            }
        }
    }

    private fun initData() {

        val apiRepository = ApiRepository()
        val gson = Gson()

        // get league ids from typed IntArray
        val leagueIds = resources.getStringArray(league_id)
        // convert league ids to String List
        //val leagueIds = leagueIdInts.map { it.toString() }
        doAsync {
            for (i in leagueIds.indices) {
                val data = gson.fromJson(
                    apiRepository.doRequest(TheSportDBApi.getLeagueData(leagueIds[i])),
                    LeagueResponse::class.java
                )
                leagueDataList.add(data.leagues[0])
                // println("test " + leagueDataList[0].leagueImage)
            }

            uiThread {
                showLeagueImageGridList(applicationContext, leagueDataList)
            }
        }
    }

    private fun showLeagueImageGridList(context: Context, leagueList: List<League>) {
        mainAdapter = LeagueListAdapter(
            context,
            leagueList
        ) {
            startActivity<LeagueDetailActivity>("leagueData" to it)
        }
        rvLeagueList.adapter = mainAdapter
    }
}
