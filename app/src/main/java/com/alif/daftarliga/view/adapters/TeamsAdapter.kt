package com.alif.daftarliga.view.adapters

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alif.daftarliga.R
import com.alif.daftarliga.model.Team
import com.alif.daftarliga.utilities.DateFormatter
import com.alif.daftarliga.view.MainActivity
import com.alif.daftarliga.view.MatchDetailsActivity
import com.bumptech.glide.Glide

class TeamsAdapter(private val teamList: ArrayList<Team>)
    : RecyclerView.Adapter<TeamsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.team_item, parent, false))
    }

    override fun getItemCount(): Int = teamList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(teamList[position])
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val teamName: TextView =  view.findViewById(R.id.tv_team_name)
        private val teamImage: ImageView = view.findViewById(R.id.img_team)
        private val leagueName: TextView = view.findViewById(R.id.tv_league_name_from_team)

        @SuppressLint("SetTextI18n")
        fun bindItem(item: Team) {
            teamName.text = item.strTeam
            Glide.with(view.context).load(item.strTeamBadge).into(teamImage)
            leagueName.text = item.strLeague
            /*view.setOnClickListener {
                val idEvent = item.idTeam
                val intent = Intent(view.context, MatchDetailsActivity::class.java)
                intent.putExtra(MainActivity.ID_EVENT_KEY, idEvent)
                intent.putExtra(MainActivity.ID_HOME_TEAM_KEY, idHomeTeam)
                intent.putExtra(MainActivity.ID_AWAY_TEAM_KEY, idAwayTeam)
                view.context.startActivity(intent)
            }*/
        }
    }
}