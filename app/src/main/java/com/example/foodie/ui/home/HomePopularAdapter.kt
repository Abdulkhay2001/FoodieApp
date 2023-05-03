package com.example.foodie.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodie.R
import com.example.foodie.model.MenuModel
import com.example.foodie.model.callback.RecyclerViewItemClick

class HomePopularAdapter(
    private val popular: List<MenuModel>,
    val callback: RecyclerViewItemClick
) : RecyclerView.Adapter<HomePopularAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.home_item, parent, false)
        return ViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.img.setImageResource(R.drawable.food_image)
        holder.name.text = popular[position].name
        holder.price.text = "${popular[position].price} TJS"

        holder.initContent(popular[position])
    }

    override fun getItemCount(): Int = popular.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val img = itemView.findViewById<ImageView>(R.id.home_img)
        val name = itemView.findViewById<TextView>(R.id.tv_home_name)
        val price = itemView.findViewById<TextView>(R.id.tv_home_price)

        fun initContent(item: MenuModel) {
            itemView.rootView.setOnClickListener {
                callback.onItemClickCallback(item)
            }
        }
    }
}