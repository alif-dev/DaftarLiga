package com.alif.daftarliga

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.liga_item.view.*

class DaftarLigaAdapter(private val context: Context, private val items: List<Item>, private val listener: (Item) -> Unit)
    : RecyclerView.Adapter<DaftarLigaAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.liga_item, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position], listener)
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        //private val name = view.findViewById<TextView>(R.id.name)
        //private val image = view.findViewById<ImageView>(R.id.image)

        fun bindItem(items: Item, listener: (Item) -> Unit) {
//            itemView.name.text = items.name
            items.image?.let { Picasso.get().load(it).fit().into(itemView.image) }
            containerView.setOnClickListener { listener(items) }
        }
    }
}