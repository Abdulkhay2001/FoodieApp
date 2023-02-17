package com.example.foodie.ui.shoppingCart

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.foodie.R
import com.example.foodie.databinding.ActivityShoppingCartBinding
import com.example.foodie.databinding.ShoppingCartItemBinding
import com.example.foodie.ui.activity.OrderedActivity

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

        var allPrice = 0


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
                    var c = sh.count + 1
                    menuView.tvCount.text = (c).toString()
                    menuView.tvPriceShoppingCart.text = "${price * c} TJS"
                    sh.count = c
                    model.update(sh)
                    binding.btnOrdered.text = "ORDER FOR ${model.price()} TJS"
                }

                menuView.tvMinus.setOnClickListener {
                    if (menuView.tvCount.text.toString().toInt() > 1) {
                        var c = sh.count - 1
                        menuView.tvCount.text = (c).toString()
                        menuView.tvPriceShoppingCart.text = "${price * c} TJS"
                        sh.count = c
                        model.update(sh)
                        binding.btnOrdered.text = "ORDER FOR ${model.price()} TJS"

                    } else {
                        val userId = this.getSharedPreferences("prefs", Context.MODE_PRIVATE)
                            .getInt("user_id", -1)
                        binding.llShoppingCart.removeAllViews()
                        model.delete(sh, userId)
                        binding.btnOrdered.text = "ORDER FOR ${model.price()} TJS"

                        if (model.db.shoppingCartDao().getShoppingCart(userId).isEmpty()){
                            binding.tvShoppingCartIsEmpty.visibility = View.VISIBLE
                            binding.llShoppingCart.visibility = View.GONE
                            binding.llOrder.visibility = View.GONE
                        }else{
                            binding.tvShoppingCartIsEmpty.visibility = View.GONE
                            binding.llShoppingCart.visibility = View.VISIBLE
                            binding.llOrder.visibility = View.VISIBLE
                        }
                    }
                }

                allPrice += model.db.menuDao().getMenu(sh.menuId).price * sh.count
                binding.llShoppingCart.addView(menuView.root)

            }

            binding.btnOrdered.text = "ORDER FOR ${model.price()} TJS"


            binding.btnOrdered.setOnClickListener {
                startActivity(Intent(this, OrderedActivity::class.java))
                overridePendingTransition(R.anim.slidein, R.anim.slideout)
                binding.llShoppingCart.removeAllViews()
                model.deleteAll()

            }

        }


        val userId = this.getSharedPreferences("prefs", Context.MODE_PRIVATE)
            .getInt("user_id", -1)
        if (model.db.shoppingCartDao().getShoppingCart(userId).isEmpty()){
            binding.tvShoppingCartIsEmpty.visibility = View.VISIBLE
            binding.llShoppingCart.visibility = View.GONE
            binding.llOrder.visibility = View.GONE
        }else{
            binding.tvShoppingCartIsEmpty.visibility = View.GONE
            binding.llShoppingCart.visibility = View.VISIBLE
            binding.llOrder.visibility = View.VISIBLE
        }

    }
}