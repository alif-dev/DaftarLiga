package com.alif.daftarliga.view.adapters

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.alif.daftarliga.R
import com.alif.daftarliga.model.Event
import com.alif.daftarliga.utilities.DateFormatter
import com.bumptech.glide.Glide
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.match_item.*

class NextMatchesAdapter(private val nextMatchList: ArrayList<Event>)
    : RecyclerView.Adapter<NextMatchesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.match_item, parent, false))
    }

    override fun getItemCount(): Int = nextMatchList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(nextMatchList[position])
    }

    // class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val matchDate: TextView = view.findViewById(R.id.match_date)
        private val homeTeamName: TextView =  view.findViewById(R.id.home_team_name)
        private val awayTeamName: TextView = view.findViewById(R.id.away_team_name)
        private val homeScore: TextView = view.findViewById(R.id.home_score)
        private val awayScore: TextView = view.findViewById(R.id.away_score)

        @SuppressLint("SetTextI18n")
        fun bindItem(item: Event) {
            matchDate.text = DateFormatter.formatDateToCommon(item.dateEvent) + "  " + item.strTimeLocal?.dropLast(3)
            homeTeamName.text = item.strHomeTeam
            awayTeamName.text = item.strAwayTeam
            homeScore.text = "-"
            awayScore.text = "-"
        }
    }
}