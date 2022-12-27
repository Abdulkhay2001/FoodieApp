package com.example.foodie.ui.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodie.R
import com.example.foodie.model.MenuModel

class FavoriteAdapter(val favorite: List<MenuModel>) :
    RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.favorite_item, parent, false)
        return FavoriteViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int = favorite.size

    class FavoriteViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }
}