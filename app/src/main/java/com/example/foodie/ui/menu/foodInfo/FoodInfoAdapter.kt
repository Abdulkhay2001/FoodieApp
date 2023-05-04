package com.example.foodie.ui.menu.foodInfo

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodie.R
import com.example.foodie.model.MenuModel
import com.example.foodie.model.ShoppingCartModel
import com.example.foodie.model.callback.RecyclerViewItemClick

class FoodInfoAdapter(val food: List<MenuModel>, val callback: RecyclerViewItemClick) :
    RecyclerView.Adapter<FoodInfoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.food_info_item, parent, false)
        return ViewHolder(inflater)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.img.setImageResource(R.drawable.food_image)
        holder.name.text = food[position].name
        holder.price.text = "${food[position].price} TJS"

        holder.initContent(food[position])
        holder.initAdd(food[position])
    }

    override fun getItemCount(): Int = food.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

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

        val img = itemView.findViewById<ImageView>(R.id.img_rec)
        val name = itemView.findViewById<TextView>(R.id.tv_name_rec)
        val price = itemView.findViewById<TextView>(R.id.tv_price_rec)
        val plus = itemView.findViewById<ImageView>(R.id.img_add)
    }
}