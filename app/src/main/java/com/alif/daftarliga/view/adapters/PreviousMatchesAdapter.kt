package com.alif.daftarliga.view.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alif.daftarliga.R
import com.alif.daftarliga.model.Event
import com.alif.daftarliga.utilities.DateFormatter

class PreviousMatchesAdapter(private val prevMatchList: ArrayList<Event>)
    : RecyclerView.Adapter<PreviousMatchesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.match_item, parent, false))
    }

    override fun getItemCount(): Int = prevMatchList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(prevMatchList[position])
    }

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
            homeScore.text = item.intHomeScore
            awayScore.text = item.intAwayScore
        }
    }
}