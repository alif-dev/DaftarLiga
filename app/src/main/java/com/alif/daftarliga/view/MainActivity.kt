package com.alif.daftarliga.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alif.daftarliga.R.array.league_id
import com.alif.daftarliga.R.color.mainBackground
import com.alif.daftarliga.model.League
import com.alif.daftarliga.model.webservice.ApiRepository
import com.alif.daftarliga.presenter.MainPresenter
import com.alif.daftarliga.view.adapters.LeagueListAdapter
import com.alif.daftarliga.view.viewinterfaces.MainView
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MainActivity : AppCompatActivity(),
    MainView {
    private lateinit var rvLeagueList: RecyclerView
    private lateinit var mainAdapter: LeagueListAdapter
    private lateinit var presenter: MainPresenter

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

        // get league ids from typed Array
        val leagueIds = resources.getStringArray(league_id)

        presenter = MainPresenter(this, apiRepository, gson)
        presenter.getLeagueDataFromAPI(leagueIds)
    }

    override fun showLeagueImageGridList(leagueList: List<League>) {
        mainAdapter = LeagueListAdapter(
            applicationContext,
            leagueList
        ) {
            startActivity<LeagueDetailActivity>("leagueData" to it)
        }
        rvLeagueList.adapter = mainAdapter
    }
}
