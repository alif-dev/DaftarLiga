package com.alif.daftarliga.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.alif.daftarliga.R
import com.alif.daftarliga.model.FavoriteTeam
import com.alif.daftarliga.model.database.dbFavoriteTeams
import com.alif.daftarliga.view.adapters.FavoriteTeamsAdapter
import kotlinx.android.synthetic.main.fragment_team_list.*
import kotlinx.android.synthetic.main.recyclerview_matches.*
import kotlinx.android.synthetic.main.recyclerview_teams.*
import kotlinx.android.synthetic.main.textview_no_data.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FavoriteTeamsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FavoriteTeamsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private val favoriteTeamList: ArrayList<FavoriteTeam> = ArrayList()

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
        return inflater.inflate(R.layout.fragment_favorite_teams, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // show favorite team list from database, so it uses FavoriteTeam model not Team one
        showFavoriteTeamList()
    }

    private fun showFavoriteTeamList() {
        favoriteTeamList.clear()
        context?.dbFavoriteTeams?.use {
            val result = select(FavoriteTeam.TABLE_FAVORITE_TEAM)
            val favoriteList = result.parseList(classParser<FavoriteTeam>())
            favoriteTeamList.addAll(favoriteList)
            if (favoriteList.isNotEmpty()) {
                tv_no_data.visibility = View.GONE
                rv_teams.layoutManager = LinearLayoutManager(activity)
                rv_teams.setHasFixedSize(true)
                rv_teams.adapter = FavoriteTeamsAdapter(favoriteTeamList)
            }
            else {
                rv_teams.visibility = View.INVISIBLE
                tv_no_data.visibility = View.VISIBLE
            }
        }
    }

    override fun onResume() {
        super.onResume()
        showFavoriteTeamList()
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FavoriteTeamsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FavoriteTeamsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
