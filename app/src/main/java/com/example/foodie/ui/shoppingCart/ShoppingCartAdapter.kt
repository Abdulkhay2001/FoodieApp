package com.example.foodie.ui.shoppingCart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.foodie.R

class ShoppingCartAdapter(val list: List<>) : RecyclerView.Adapter<ShoppingCartAdapter.ShoppingCartViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingCartViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.shopping_cart_item, parent, false)
        return ShoppingCartViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: ShoppingCartViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int = list.size

    class ShoppingCartViewHolder(view: View) :ViewHolder(view)  {

    }
}