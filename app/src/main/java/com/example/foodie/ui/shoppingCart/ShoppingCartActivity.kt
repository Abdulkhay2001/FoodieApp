package com.example.foodie.ui.shoppingCart

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.foodie.databinding.ActivityShoppingCartBinding
import com.example.foodie.databinding.ShoppingCartItemBinding
import com.example.foodie.ui.favorite.FavoriteViewModel
import com.example.foodie.ui.menu.SharedViewModel

class ShoppingCartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShoppingCartBinding

    private lateinit var model: ShoppingCartViewModel

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShoppingCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbarShoppingCart.imgShoppingCart.isVisible = false
        binding.toolbarShoppingCart.back.isVisible = true
        binding.toolbarShoppingCart.tvRoot.text = "Shopping cart"

        model = ViewModelProvider(this)[ShoppingCartViewModel::class.java]

        binding.toolbarShoppingCart.back.setOnClickListener {
            finish()
        }

        // list menu id:count
        model.shoppingCart.observe(this) {
            it.forEach { sh ->
                val menuView = ShoppingCartItemBinding.inflate(layoutInflater)
                menuView.tvNameShoppingCart.text = model.db.menuDao().getMenu(sh.menuId).name
                menuView.tvDescriptionShoppingCart.text = model.db.menuDao().getMenu(sh.menuId).desc

                val price = model.db.menuDao().getMenu(sh.menuId).price
                menuView.tvPriceShoppingCart.text = "${
                    model.db.menuDao().getMenu(sh.menuId).price * sh.count
                } TJS"

                menuView.tvCount.text = sh.count.toString()

                menuView.tvPlus.setOnClickListener {
                    var c = menuView.tvCount.text.toString().toInt() + 1
                    menuView.tvCount.text = (c).toString()
                    menuView.tvPriceShoppingCart.text = "${price * c} TJS"
                }

                menuView.tvMinus.setOnClickListener {
                    if (menuView.tvCount.text.toString().toInt() > 1) {
                        var c = menuView.tvCount.text.toString().toInt() - 1
                        menuView.tvCount.text = (c).toString()
                        menuView.tvPriceShoppingCart.text = "${price * c} TJS"

                    } else {
                        val userId = this.getSharedPreferences("prefs", Context.MODE_PRIVATE)
                            .getInt("user_id", -1)
                        binding.llShoppingCart.removeAllViews()
                        model.delete(sh, userId)
                    }
                }
                binding.llShoppingCart.addView(menuView.root)
            }

        }
        // list forEach {
//        menuView.tvCount = count.toString
//        menuView.name = menu.name
    }
}