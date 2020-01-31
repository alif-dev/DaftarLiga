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
import com.alif.daftarliga.model.FavoriteTeam
import com.alif.daftarliga.view.MainActivity
import com.alif.daftarliga.view.TeamDetailsActivity
import com.bumptech.glide.Glide

class FavoriteTeamsAdapter(private val teamList: ArrayList<FavoriteTeam>)
    : RecyclerView.Adapter<FavoriteTeamsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.team_item, parent, false))
    }

    override fun getItemCount(): Int = teamList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(teamList[position])
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val teamName: TextView = view.findViewById(R.id.tv_team_name)
        private val teamImage: ImageView = view.findViewById(R.id.img_team)
        private val teamCountry: TextView =  view.findViewById(R.id.tv_team_country)

        @SuppressLint("SetTextI18n")
        fun bindItem(item: FavoriteTeam) {
            teamName.text = item.strTeam
            Glide.with(view.context).load(item.strTeamBadge).into(teamImage)
            teamCountry.text = item.strCountry
            view.setOnClickListener {
                val idTeam = item.idTeam
                val intent = Intent(view.context, TeamDetailsActivity::class.java)
                intent.putExtra(MainActivity.ID_TEAM_KEY, idTeam)
                view.context.startActivity(intent)
            }
        }
    }
}