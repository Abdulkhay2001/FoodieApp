package com.example.foodie.ui.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodie.R
import com.example.foodie.model.MenuModel
import com.example.foodie.model.callback.RecyclerViewItemClick

class FavoriteAdapter(val favorite: List<MenuModel>, val callback: RecyclerViewItemClick) :
    RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.favorite_item, parent, false)
        return FavoriteViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.initContent(favorite[position])

        holder.name.text = favorite[position].name
        holder.price.text = "${favorite[position].price} somon"

    }

    override fun getItemCount(): Int = favorite.size

    inner class FavoriteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = itemView.findViewById(R.id.tv_name_favorite)
        val price: TextView = itemView.findViewById(R.id.tv_price_favorite)
        val imgFavorite: ImageView = itemView.findViewById(R.id.img_favorite)


        fun initContent(item: MenuModel) {

            itemView.rootView.setOnClickListener {
                callback.onItemClickCallback(item)
            }

            imgFavorite.setOnClickListener {
                callback.onFavoriteClick(item)
            }
                imgFavorite.setImageResource(R.drawable.ic_round_favorite_red_24)

        }
    }
}