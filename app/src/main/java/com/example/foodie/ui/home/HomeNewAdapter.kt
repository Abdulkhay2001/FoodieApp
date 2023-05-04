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

class HomeNewAdapter(private val new: List<MenuModel>, val callback: RecyclerViewItemClick) :
    RecyclerView.Adapter<HomeNewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.home_item, parent, false)
        return ViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.img.setImageResource(R.drawable.food_image)
        holder.name.text = new[position].name
        holder.price.text = "${new[position].price} TJS"

        holder.initContent(new[position])
        holder.initAdd(new[position])
    }

    override fun getItemCount(): Int = new.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val img = itemView.findViewById<ImageView>(R.id.home_img)
        val name = itemView.findViewById<TextView>(R.id.tv_home_name)
        val price = itemView.findViewById<TextView>(R.id.tv_home_price)
        val plus = itemView.findViewById<ImageView>(R.id.img_home_add)

        fun initContent(item: MenuModel) {
            itemView.rootView.setOnClickListener {
                callback.onItemClickCallback(item)
            }
        }

        fun initAdd(item: MenuModel){
            plus.setOnClickListener {
                callback.onShoppingCartClick(item)
            }
        }
    }
}