package com.alif.daftarliga.view.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.alif.daftarliga.R
import com.alif.daftarliga.model.EventResponse
import com.alif.daftarliga.model.League
import com.alif.daftarliga.model.TableResponse
import com.alif.daftarliga.model.TeamResponse
import com.alif.daftarliga.view.LeagueDetailActivity
import com.alif.daftarliga.view.MainActivity
import com.bumptech.glide.Glide

class LeagueListAdapter(
    private val leagues: List<League>,
    private val nextMatches: ArrayList<EventResponse>,
    private val prevMatches: ArrayList<EventResponse>,
    private val standings: ArrayList<TableResponse>,
    private val teams: ArrayList<TeamResponse>
) : RecyclerView.Adapter<LeagueListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.league_item, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(
            leagues[position],
            nextMatches[position],
            prevMatches[position],
            standings[position],
            teams[position]
        )
    }

    override fun getItemCount(): Int = leagues.size

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private var imgLeague: ImageView = view.findViewById(R.id.img_league_main)

        fun bindItem(
            league: League,
            nextMatches: EventResponse,
            prevMatches: EventResponse,
            standings: TableResponse,
            teams: TeamResponse
        ) {
            Glide.with(view).load(league.leagueBadge).into(imgLeague)
            view.setOnClickListener {
                val intent = Intent(view.context, LeagueDetailActivity::class.java)
                intent.putExtra(MainActivity.LEAGUE_DATA_KEY, league)
                // it is possible that there are no next matches
                if (!nextMatches.events.isNullOrEmpty()) {
                    intent.putExtra(MainActivity.NEXT_EVENTS_DATA_KEY, nextMatches)
                }
                intent.putExtra(MainActivity.PREV_EVENTS_DATA_KEY, prevMatches)
                intent.putExtra(MainActivity.STANDINGS_DATA_KEY, standings)
                intent.putExtra(MainActivity.TEAMS_DATA_KEY, teams)
                view.context.startActivity(intent)
            }
        }
    }
}