package com.alif.daftarliga.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.alif.daftarliga.R
import com.alif.daftarliga.model.Table
import com.alif.daftarliga.view.adapters.StandingsAdapter
import kotlinx.android.synthetic.main.recyclerview_standings.*
import kotlinx.android.synthetic.main.textview_no_data.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [StandingsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StandingsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var standingsList: ArrayList<Table>? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            standingsList = it.getParcelableArrayList(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_standings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!standingsList.isNullOrEmpty()) {
            tv_no_data.visibility = View.GONE
            rv_standings.layoutManager = LinearLayoutManager(activity)
            rv_standings.setHasFixedSize(true)
            rv_standings.adapter = standingsList?.let { StandingsAdapter(it) }
        } else {
            rv_standings.visibility = View.INVISIBLE
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
         * @return A new instance of fragment StandingsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: ArrayList<Table>?, param2: String) =
            StandingsFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
