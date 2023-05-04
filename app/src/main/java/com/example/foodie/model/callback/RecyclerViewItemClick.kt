package com.example.foodie.model.callback

interface RecyclerViewItemClick {
    fun onItemClickCallback(item: Any)
    fun onFavoriteClick(item: Any)
    fun onShoppingCartClick(item: Any)
}