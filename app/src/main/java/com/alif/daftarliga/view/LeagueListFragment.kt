package com.alif.daftarliga.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.alif.daftarliga.R
import com.alif.daftarliga.model.EventResponse
import com.alif.daftarliga.model.League
import com.alif.daftarliga.model.webservice.ApiRepository
import com.alif.daftarliga.presenter.MainPresenter
import com.alif.daftarliga.utilities.EspressoIdlingResource
import com.alif.daftarliga.view.adapters.LeagueListAdapter
import com.alif.daftarliga.view.viewinterfaces.MainView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_league_list.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LeagueListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LeagueListFragment : Fragment(), MainView {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var mainAdapter: LeagueListAdapter
    private lateinit var leaguePresenter: MainPresenter

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
        return inflater.inflate(R.layout.fragment_league_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
    }

    private fun initData() {

        val apiRepository = ApiRepository()
        val gson = Gson()

        // get league ids from typed Array
        val leagueIds = resources.getStringArray(R.array.league_ids)

        leaguePresenter = MainPresenter(this, apiRepository, gson)
        // memberitahukan Espresso bahwa aplikasi sedang menjalankan proses asynchronous
        EspressoIdlingResource.increment()
        leaguePresenter.getLeagueDataFromAPI(leagueIds)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LeagueListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LeagueListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun showLoading() {
        pb_league_list.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        pb_league_list.visibility = View.GONE
    }

    override fun showLeagueImageGridList(
        leagueList: List<League>,
        allLeaguesNextMatchList: ArrayList<EventResponse>,
        allLeaguesPrevMatchList: ArrayList<EventResponse>
    ) {
        // cek apakah proses asynchronous telah selesai atau belum
        if (!EspressoIdlingResource.idlingResource.isIdleNow) {
            // memberitahukan bahwa proses asynchronous telah selesai
            EspressoIdlingResource.decrement()
        }

        mainAdapter = LeagueListAdapter(leagueList, allLeaguesNextMatchList, allLeaguesPrevMatchList)
        rv_league_list.layoutManager = GridLayoutManager(activity, 2)
        rv_league_list.setHasFixedSize(true)
        rv_league_list.adapter = mainAdapter
    }
}
