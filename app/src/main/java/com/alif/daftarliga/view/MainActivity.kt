package com.alif.daftarliga.view

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
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
    companion object {
        val LEAGUE_DATA_KEY = "leagueData"
    }
    private lateinit var rvLeagueList: RecyclerView
    private lateinit var mainAdapter: LeagueListAdapter
    private lateinit var presenter: MainPresenter
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        relativeLayout {
            backgroundColor = ContextCompat.getColor(context, mainBackground)
            lparams(matchParent, matchParent)

            rvLeagueList = recyclerView {
                layoutManager = GridLayoutManager(context, 2)
            }

            progressBar = progressBar {
            }.lparams {
                centerInParent()
            }
        }

        initData()
    }

    private fun initData() {

        val apiRepository = ApiRepository()
        val gson = Gson()

        // get league ids from typed Array
        val leagueIds = resources.getStringArray(league_id)

        presenter = MainPresenter(this, apiRepository, gson)
        presenter.getLeagueDataFromAPI(leagueIds)
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.INVISIBLE
    }

    override fun showLeagueImageGridList(leagueList: List<League>) {
        mainAdapter = LeagueListAdapter(
            applicationContext,
            leagueList
        ) {
            startActivity<LeagueDetailActivity>(LEAGUE_DATA_KEY to it)
        }
        rvLeagueList.adapter = mainAdapter
    }
}
