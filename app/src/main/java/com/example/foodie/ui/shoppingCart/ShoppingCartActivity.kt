package com.example.foodie.ui.shoppingCart

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.example.foodie.R
import com.example.foodie.databinding.ActivityShoppingCartBinding

class ShoppingCartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShoppingCartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShoppingCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbarShoppingCart.imgShoppingCart.isVisible = false
        binding.toolbarShoppingCart.back.isVisible = true
        binding.toolbarShoppingCart.tvRoot.text = "Shopping cart"



    }
}