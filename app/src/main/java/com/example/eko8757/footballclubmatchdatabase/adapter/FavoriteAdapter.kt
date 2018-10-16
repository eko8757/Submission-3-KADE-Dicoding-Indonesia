package com.example.eko8757.footballclubmatchdatabase.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.eko8757.footballclubmatchdatabase.R
import com.example.eko8757.footballclubmatchdatabase.db.Favorite
import kotlinx.android.synthetic.main.item_list.view.*

class FavoriteAdapter(private val favorite: List<Favorite>,
                      private val listener: (Favorite) -> Unit) : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {


    class FavoriteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun binItem(favorite: Favorite, listener: (Favorite) -> Unit) {
            itemView.date.text = favorite.dateEvent
            itemView.club_home.text = favorite.homeTeam
            itemView.score_home.text = favorite.homeScore
            itemView.score_away.text = favorite.awayScore
            itemView.club_away.text = favorite.awayTeam
            itemView.vs.text = "VS"
            itemView.setOnClickListener {
                listener(favorite)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false))

    }

    override fun getItemCount(): Int = favorite.size

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.binItem(favorite[position], listener)
    }


}