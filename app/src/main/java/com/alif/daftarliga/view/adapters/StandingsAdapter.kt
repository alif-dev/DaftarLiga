package com.alif.daftarliga.view.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alif.daftarliga.R
import com.alif.daftarliga.model.Table

class StandingsAdapter(private val standingsList: ArrayList<Table>)
    : RecyclerView.Adapter<StandingsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.standings_item, parent, false))
    }

    override fun getItemCount(): Int = standingsList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(standingsList[position])
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val teamName: TextView =  view.findViewById(R.id.team_name_row)
        private val teamPlayed: TextView = view.findViewById(R.id.team_played_row)
        private val teamGoalsFor: TextView = view.findViewById(R.id.team_goals_for_row)
        private val teamGoalsAgainst: TextView = view.findViewById(R.id.team_goals_against_row)
        private val teamGoalsDifference: TextView = view.findViewById(R.id.team_goals_difference_row)
        private val teamWin: TextView = view.findViewById(R.id.team_win_row)
        private val teamDraw: TextView = view.findViewById(R.id.team_draw_row)
        private val teamLoss: TextView = view.findViewById(R.id.team_loss_row)
        private val teamTotal: TextView = view.findViewById(R.id.team_total_row)

        @SuppressLint("SetTextI18n")
        fun bindItem(item: Table) {
            teamName.text = item.name
            teamPlayed.text = item.played
            teamGoalsFor.text = item.goalsFor
            teamGoalsAgainst.text = item.goalsAgainst
            teamGoalsDifference.text = item.goalsDifference
            teamWin.text = item.win
            teamDraw.text = item.draw
            teamLoss.text = item.loss
            teamTotal.text = item.total
        }
    }
}