package com.alif.daftarliga.view.adapters

import android.content.Context
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.alif.daftarliga.model.EventResponse
import com.alif.daftarliga.model.League
import com.alif.daftarliga.view.LeagueDetailActivity
import com.alif.daftarliga.view.LeagueItemUI
import com.alif.daftarliga.view.MainActivity
import com.bumptech.glide.Glide
import kotlinx.android.extensions.LayoutContainer
import org.jetbrains.anko.AnkoContext

class LeagueListAdapter(
    private val context: Context,
    private val leagues: List<League>,
    private val nextMatches: ArrayList<EventResponse>,
    private val prevMatches: ArrayList<EventResponse>
) : RecyclerView.Adapter<LeagueListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LeagueItemUI().createView(
                AnkoContext.create(context, parent)
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(leagues[position], nextMatches[position], prevMatches[position])
    }

    override fun getItemCount(): Int = leagues.size

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        private var imgLeague: ImageView = itemView.findViewById(LeagueItemUI.img_league)

        fun bindItem(league: League, nextMatches: EventResponse, prevMatches: EventResponse) {
            Glide.with(containerView).load(league.leagueBadge).into(imgLeague)
            containerView.setOnClickListener {
                val intent = Intent(containerView.context, LeagueDetailActivity::class.java)
                intent.putExtra(MainActivity.LEAGUE_DATA_KEY, league)
                if (!nextMatches.events.isNullOrEmpty()) {
                    intent.putExtra(MainActivity.NEXT_EVENTS_DATA_KEY, nextMatches)
                }
                intent.putExtra(MainActivity.PREV_EVENTS_DATA_KEY, prevMatches)
                containerView.context.startActivity(intent)
            }
        }
    }
}