package com.alif.daftarliga.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.alif.daftarliga.R
import com.alif.daftarliga.model.Team
import com.alif.daftarliga.model.TeamResponse
import com.alif.daftarliga.model.webservice.ApiRepository
import com.alif.daftarliga.presenter.SearchTeamPresenter
import com.alif.daftarliga.utilities.EspressoIdlingResource
import com.alif.daftarliga.view.adapters.TeamsAdapter
import com.alif.daftarliga.view.viewinterfaces.SearchTeamView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_search_team.*
import kotlinx.android.synthetic.main.recyclerview_teams.*
import kotlinx.android.synthetic.main.textview_no_data.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SearchTeamFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchTeamFragment : Fragment(), SearchView.OnQueryTextListener, SearchTeamView {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private val apiRepository = ApiRepository()
    private val gson = Gson()
    private lateinit var searchTeamPresenter: SearchTeamPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_team, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        search_view_team.setOnQueryTextListener(this)
        rv_teams.layoutManager = LinearLayoutManager(activity)
        rv_teams.setHasFixedSize(true)
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SearchTeamFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchTeamFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        searchTeamPresenter = SearchTeamPresenter(this, apiRepository, gson)
        // memberitahukan Espresso bahwa aplikasi sedang menjalankan proses asynchronous
        EspressoIdlingResource.increment()
        searchTeamPresenter.searchTeamDataFromAPI(query)
        search_view_team.clearFocus()
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean = false

    override fun showLoading() {
        pb_search_team.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        pb_search_team.visibility = View.GONE
    }

    override fun showSearchedTeams(searchedTeamList: ArrayList<TeamResponse>) {
        // cek apakah proses asynchronous telah selesai atau belum
        if (!EspressoIdlingResource.idlingResource.isIdleNow) {
            // memberitahukan bahwa proses asynchronous telah selesai
            EspressoIdlingResource.decrement()
        }

        val searchedTeamListObject: TeamResponse = searchedTeamList[0]
        val searchedTeamListData: ArrayList<Team>? = ArrayList()
        // if the searched match list data is null or empty from the API then do not show recyclerview
        // but show "NO DATA" textview but the opposite if the data is not null or empty
        if (!searchedTeamListObject.teams.isNullOrEmpty()) {
            showRecycleView()
            // extract elements in EventResponse2 to list
            for (element in searchedTeamListObject.teams) {
                searchedTeamListData?.add(element)
            }

            // filter the team list for only Soccer type
            val soccerTeamList: List<Team>? =
                searchedTeamListData?.filter { team -> team.strSport == "Soccer" }

            // if the searched team list data is null or empty then do not show recyclerview
            // instead show "NO DATA" textview but the opposite if the data is not null or empty
            if (!soccerTeamList.isNullOrEmpty()) {
                showRecycleView()
                rv_teams.adapter = TeamsAdapter(ArrayList(soccerTeamList))
            } else {
                showNoDataTextView()
            }
        } else {
           showNoDataTextView()
        }
    }

    private fun showRecycleView() {
        tv_no_data.visibility = View.GONE
        rv_teams.visibility = View.VISIBLE
    }

    private fun showNoDataTextView() {
        rv_teams.visibility = View.INVISIBLE
        tv_no_data.visibility = View.VISIBLE
    }
}
