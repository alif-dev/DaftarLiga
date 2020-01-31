package com.alif.daftarliga.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.alif.daftarliga.R
import com.alif.daftarliga.model.FavoriteMatch
import com.alif.daftarliga.model.database.dbPrevMatches
import com.alif.daftarliga.view.adapters.FavoritePrevMatchesAdapter
import kotlinx.android.synthetic.main.recyclerview_matches.*
import kotlinx.android.synthetic.main.textview_no_data.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FavoritePreviousMatchesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FavoritePreviousMatchesFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private val favoriteMatchList: ArrayList<FavoriteMatch> = ArrayList()

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
        return inflater.inflate(R.layout.fragment_favorite_previous_matches, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // show favorite match list from database
        showFavoriteMatchList()
    }

    private fun showFavoriteMatchList() {
        favoriteMatchList.clear()
        context?.dbPrevMatches?.use {
            val result = select(FavoriteMatch.TABLE_FAVORITE_MATCH)
            val favoriteList = result.parseList(classParser<FavoriteMatch>())
            favoriteMatchList.addAll(favoriteList)
            if (favoriteList.isNotEmpty()) {
                tv_no_data.visibility = View.GONE
                rv_matches.layoutManager = LinearLayoutManager(activity)
                rv_matches.setHasFixedSize(true)
                rv_matches.adapter = FavoritePrevMatchesAdapter(favoriteMatchList)
            } else {
                rv_matches.visibility = View.INVISIBLE
                tv_no_data.visibility = View.VISIBLE
            }
        }
    }

    override fun onResume() {
        super.onResume()
        showFavoriteMatchList()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            // refresh the fragment when opened (detach and attach it again)
            fragmentManager?.beginTransaction()?.detach(this)?.attach(this)?.commit()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FavoritePreviousMatchesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FavoritePreviousMatchesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
