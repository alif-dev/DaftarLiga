package com.alif.daftarliga.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.alif.daftarliga.R
import com.alif.daftarliga.model.Event2
import com.alif.daftarliga.model.EventResponse2
import com.alif.daftarliga.model.webservice.ApiRepository
import com.alif.daftarliga.presenter.SearchMatchPresenter
import com.alif.daftarliga.view.adapters.MatchesAdapter2
import com.alif.daftarliga.view.viewinterfaces.SearchMatchView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_search_match.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SearchMatchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchMatchFragment : Fragment(), SearchView.OnQueryTextListener, SearchMatchView {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private val apiRepository = ApiRepository()
    private val gson = Gson()
    private lateinit var searchMatchPresenter: SearchMatchPresenter

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
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_search_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        search_view_match.setOnQueryTextListener(this)

        rv_searched_matches.layoutManager = LinearLayoutManager(activity)
        rv_searched_matches.setHasFixedSize(true)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        searchMatchPresenter = SearchMatchPresenter(this, apiRepository, gson)
        searchMatchPresenter.searchMatchDataFromAPI(query)
        search_view_match.clearFocus()
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean = false

    override fun showLoading() {
        pb_search_match.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        pb_search_match.visibility = View.GONE
    }

    override fun showSearchedMatches(searchedMatchList: ArrayList<EventResponse2>) {
        val searchedMatchListObject: EventResponse2 = searchedMatchList[0]
        val searchedMatchListData: ArrayList<Event2>? = ArrayList()
        // if the searched match list data is null or empty from the API then do not show recyclerview
        // but show "NO DATA" textview but the opposite if the data is not null or empty
        if (!searchedMatchListObject.event.isNullOrEmpty()) {
            tv_no_data_search.visibility = View.GONE
            // extract elements in EventResponse2 to list
            for (element in searchedMatchListObject.event) {
                searchedMatchListData?.add(element)
            }

            // filter the match list for only Soccer type matches
            val soccerMatchList: List<Event2>? =
                searchedMatchListData?.filter { event2 -> event2.strSport == "Soccer" }

            // if the soccer match list data is null or empty then do not show recyclerview
            // but show "NO DATA" textview but the opposite if the data is not null or empty
            if (!soccerMatchList.isNullOrEmpty()) {
                tv_no_data_search.visibility = View.GONE
                rv_searched_matches.adapter = MatchesAdapter2(ArrayList(soccerMatchList))
            } else {
                rv_searched_matches.adapter = null
                tv_no_data_search.visibility = View.VISIBLE
            }
        } else {
            rv_searched_matches.adapter = null
            tv_no_data_search.visibility = View.VISIBLE
        }
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param searchedMatchList Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SearchMatchFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchMatchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
