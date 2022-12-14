package com.example.foodie.ui.foodInfo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodie.R
import com.example.foodie.model.MenuModel

class FoodInfoAdapter(val food: List<MenuModel>): RecyclerView.Adapter<FoodInfoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.food_info_item, parent, false)
        return ViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.img.setImageResource(R.drawable.food_image)
        holder.name.text = food[position].name
        holder.price.text =food[position].price.toString()
    }

    override fun getItemCount(): Int = food.size

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val img = itemView.findViewById<ImageView>(R.id.img_rec)
        val name = itemView.findViewById<TextView>(R.id.tv_name_rec)
        val price = itemView.findViewById<TextView>(R.id.tv_price_rec)
    }
}