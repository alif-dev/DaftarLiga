package com.alif.daftarliga.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.alif.daftarliga.R
import com.alif.daftarliga.model.Event
import com.alif.daftarliga.view.adapters.PreviousMatchesAdapter
import kotlinx.android.synthetic.main.recyclerview_matches.*
import kotlinx.android.synthetic.main.textview_no_data.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PreviousMatchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PreviousMatchFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var prevMatchList: ArrayList<Event>? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            prevMatchList = it.getParcelableArrayList(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_previous_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // if the prev match list data is null or empty from the API then do not show recyclerview
        // but show "no data" textview
        if (!prevMatchList.isNullOrEmpty()) {
            rv_matches.layoutManager = LinearLayoutManager(activity)
            rv_matches.setHasFixedSize(true)
            rv_matches.adapter = prevMatchList?.let { PreviousMatchesAdapter(it) }
        } else {
            rv_matches.visibility = View.INVISIBLE
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
         * @return A new instance of fragment PreviousMatchFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: ArrayList<Event>?, param2: String) =
            PreviousMatchFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
