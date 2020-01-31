package com.alif.daftarliga.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.alif.daftarliga.R
import com.alif.daftarliga.model.Team
import com.alif.daftarliga.view.adapters.TeamsAdapter
import kotlinx.android.synthetic.main.recyclerview_matches.*
import kotlinx.android.synthetic.main.recyclerview_teams.*
import kotlinx.android.synthetic.main.textview_no_data.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TeamListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TeamListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var teamList: ArrayList<Team>? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            teamList = it.getParcelableArrayList(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // if the team list data is null or empty from the API then do not show recyclerview
        // instead show "no data" textview
        if (!teamList.isNullOrEmpty()) {
            tv_no_data.visibility = View.GONE
            rv_teams.layoutManager = LinearLayoutManager(activity)
            rv_teams.setHasFixedSize(true)
            rv_teams.adapter = teamList?.let { TeamsAdapter(it) }
        } else {
            rv_teams.visibility = View.INVISIBLE
            tv_no_data.visibility = View.VISIBLE
        }
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TeamListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: ArrayList<Team>?, param2: String) =
            TeamListFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
