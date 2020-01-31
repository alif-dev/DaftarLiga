package com.alif.daftarliga.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.alif.daftarliga.R
import com.alif.daftarliga.model.Team
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_team_details.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = MainActivity.TEAM_DATA_KEY
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TeamDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TeamDetailsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        return inflater.inflate(R.layout.fragment_team_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val teamData: Team? = arguments?.getParcelable(ARG_PARAM1)

        tv_team_name_dtl.text = teamData?.strTeam
        tv_team_alternate_name.text = teamData?.strAlternate
        Glide.with(view.context).load(teamData?.strTeamLogo).into(img_team_logo)
        tv_team_formed_year.text = teamData?.intFormedYear
        tv_team_league.text = teamData?.strLeague
        tv_team_player_gender.text = teamData?.strGender
        tv_team_country_dtl.text = teamData?.strCountry
        tv_team_website.text = teamData?.strWebsite
        tv_team_facebook.text = teamData?.strFacebook
        tv_team_twitter.text = teamData?.strTwitter
        tv_team_youtube.text = teamData?.strYoutube
        tv_team_rss_feed.text = teamData?.strRSS
        tv_team_stadium_name.text = teamData?.strStadium
        Glide.with(view.context).load(teamData?.strStadiumThumb).into(img_team_stadium)
        tv_team_description.text = teamData?.strDescriptionEN
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TeamDetailsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: Team, param2: String) =
            TeamDetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
