package com.alif.daftarliga.view.adapters

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alif.daftarliga.R
import com.alif.daftarliga.model.Event
import com.alif.daftarliga.utilities.DateFormatter
import com.alif.daftarliga.view.MainActivity
import com.alif.daftarliga.view.MatchDetailsActivity

class NextMatchesAdapter(private val nextMatchList: ArrayList<Event>)
    : RecyclerView.Adapter<NextMatchesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.match_item, parent, false))
    }

    override fun getItemCount(): Int = nextMatchList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(nextMatchList[position])
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val matchDate: TextView = view.findViewById(R.id.tv_match_date)
        private val homeTeamName: TextView =  view.findViewById(R.id.home_team_name)
        private val awayTeamName: TextView = view.findViewById(R.id.away_team_name)
        private val homeScore: TextView = view.findViewById(R.id.home_score)
        private val awayScore: TextView = view.findViewById(R.id.away_score)

        @SuppressLint("SetTextI18n")
        fun bindItem(item: Event) {
            matchDate.text = DateFormatter.formatDateToCommon(item.dateEvent) + "  " + item.strTime?.dropLast(3)
            homeTeamName.text = item.strHomeTeam
            awayTeamName.text = item.strAwayTeam
            if (item.intHomeScore.isNullOrBlank()) homeScore.text = "-" else homeScore.text = item.intHomeScore
            if (item.intAwayScore.isNullOrBlank()) awayScore.text = "-" else awayScore.text = item.intAwayScore
            view.setOnClickListener {
                val idEvent = item.idEvent
                val idHomeTeam = item.idHomeTeam
                val idAwayTeam = item.idAwayTeam
                val intent = Intent(view.context, MatchDetailsActivity::class.java)
                intent.putExtra(MainActivity.ID_EVENT_KEY, idEvent)
                intent.putExtra(MainActivity.ID_HOME_TEAM_KEY, idHomeTeam)
                intent.putExtra(MainActivity.ID_AWAY_TEAM_KEY, idAwayTeam)
                intent.putExtra(MainActivity.EVENT_DATA_KEY, MainActivity.NEXT_EVENT_DATA_VALUE)
                view.context.startActivity(intent)
            }
        }
    }
}