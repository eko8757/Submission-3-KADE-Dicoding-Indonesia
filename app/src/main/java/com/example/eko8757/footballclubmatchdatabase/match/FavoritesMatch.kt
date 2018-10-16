package com.example.eko8757.footballclubmatchdatabase.match

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar

import com.example.eko8757.footballclubmatchdatabase.R
import com.example.eko8757.footballclubmatchdatabase.adapter.FavoriteAdapter
import com.example.eko8757.footballclubmatchdatabase.db.Favorite
import com.example.eko8757.footballclubmatchdatabase.db.database
import com.example.eko8757.footballclubmatchdatabase.main.DetailActivity
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.intentFor

class FavoritesMatch : Fragment() {

    private var favorites: MutableList<Favorite> = mutableListOf()
    private lateinit var favoriteList: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var adapter: FavoriteAdapter

    private var listener: OnFragmentInteractionListener? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val rootView: View = inflater.inflate(R.layout.fragment_favorites, container, false)
        favoriteList = rootView.findViewById(R.id.favorite_match) as RecyclerView
        favoriteList.layoutManager = LinearLayoutManager(activity)

        adapter = FavoriteAdapter(favorites) {
            activity?.startActivity(intentFor<DetailActivity>("idEvent" to "${it.eventId}"))
        }

        favoriteList.adapter = adapter
        showFavorite()
        return rootView
    }

    private fun showFavorite() {
        context?.database?.use {
            val result = select(Favorite.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<Favorite>())
            favorites.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }

}
