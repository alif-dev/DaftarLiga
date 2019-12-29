package com.alif.daftarliga.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.alif.daftarliga.R
import com.alif.daftarliga.model.League
import kotlinx.android.synthetic.main.fragment_league_details.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private val ARG_PARAM1 = MainActivity.LEAGUE_DATA_KEY
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LeagueDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LeagueDetailsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: League? = null
    private var param2: String? = null

    private var leagueName: String = ""
    private var leagueAlternateName: String = ""
    private var leagueFormedYear: String = ""
    private var leagueFirstEvent: String = ""
    private var leaguePlayerGender: String = ""
    private var leagueCountry: String = ""
    private var leagueWebsite: String = ""
    private var leagueFacebook: String = ""
    private var leagueTwitter: String = ""
    private var leagueYoutube: String = ""
    private var leagueRSSFeed: String = ""
    private var leagueDescription: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getParcelable(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_league_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val leagueData: League? = arguments?.getParcelable(ARG_PARAM1)

        tv_league_name.text = leagueData?.leagueName
        tv_league_alternate_name.text = leagueData?.leagueAlternateName
        tv_league_formed_year.text = leagueData?.leagueFormedYear
        tv_league_first_event.text = leagueData?.leagueFirstEvent
        tv_league_player_gender.text = leagueData?.leaguePlayerGender
        tv_league_country.text = leagueData?.leagueCountry
        tv_league_website.text = leagueData?.leagueWebsite
        tv_league_facebook.text = leagueData?.leagueFacebook
        tv_league_twitter.text = leagueData?.leagueTwitter
        tv_league_youtube.text = leagueData?.leagueYoutube
        tv_league_rss_feed.text = leagueData?.leagueRSSFeed
        tv_league_description.text = leagueData?.leagueDescription
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BlankFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: League?, param2: String) =
            LeagueDetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
