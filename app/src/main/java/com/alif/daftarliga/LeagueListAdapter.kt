package com.alif.daftarliga

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.liga_item.view.*
import org.jetbrains.anko.AnkoContext

class LeagueListAdapter(private val context: Context, private val leagues: List<League>, private val listener: (League) -> Unit)
    : RecyclerView.Adapter<LeagueListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LeagueItemUI().createView(AnkoContext.create(context, parent)))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(leagues[position], listener)
    }

    override fun getItemCount(): Int = leagues.size

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        //private val name = view.findViewById<TextView>(R.id.name)
        //private val image = view.findViewById<ImageView>(R.id.image)

        var img_league: ImageView = itemView.findViewById(LeagueItemUI.img_league)

        fun bindItem(items: League, listener: (League) -> Unit) {
//            itemView.name.text = items.name
            items.image?.let { Picasso.get().load(it).fit().into(img_league) }
            containerView.setOnClickListener { listener(items) }
        }
    }
}