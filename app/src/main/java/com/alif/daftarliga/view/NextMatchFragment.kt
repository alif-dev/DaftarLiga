package com.alif.daftarliga.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.alif.daftarliga.R
import com.alif.daftarliga.model.Event
import com.alif.daftarliga.view.adapters.NextMatchesAdapter
import kotlinx.android.synthetic.main.fragment_next_match.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NextMatchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NextMatchFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var nextMatchList: ArrayList<Event>? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            nextMatchList = it.getParcelableArrayList(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_next_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // if the next match list data is null or empty from the API then do not show recyclerview
        // but show "no data" textview
        if (!nextMatchList.isNullOrEmpty()) {
            rv_next_matches.layoutManager = LinearLayoutManager(activity)
            rv_next_matches.setHasFixedSize(true)
            rv_next_matches.adapter = nextMatchList?.let { NextMatchesAdapter(it) }!!
        } else {
            tv_no_data_next.visibility = View.VISIBLE
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment NextMatchFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: ArrayList<Event>?, param2: String) =
            NextMatchFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
