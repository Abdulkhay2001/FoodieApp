package com.example.foodie.ui.menu

import android.view.LayoutInflater
import android.view.MenuItem
import android.view.ScrollCaptureCallback
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodie.R
import com.example.foodie.databinding.MenuItemBinding
import com.example.foodie.model.MenuModel
import com.example.foodie.model.callback.RecyclerViewItemClick

class MenuAdapter(val menu: List<MenuModel>, val callback: RecyclerViewItemClick) : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.menu_item, parent, false)
        return MenuViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.initContent(menu[position])

        holder.name.text = menu[position].name
        holder.price.text = "${menu[position].price.toString()} somon"
    }

    override fun getItemCount(): Int = menu.size

  inner  class MenuViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun initContent(item: MenuModel){

            itemView.rootView.setOnClickListener {
                callback.onItemClickCallback(item)
            }

        }

        val name: TextView = itemView.findViewById(R.id.tv_name_menu)
        val price: TextView = itemView.findViewById(R.id.tv_price_menu)
    }

}
