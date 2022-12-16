package com.example.foodie.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.foodie.R
import com.example.foodie.databinding.ActivityOrderedBinding
import com.example.foodie.databinding.ActivityRootBinding

class OrderedActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOrderedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOrderedBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.backOrdered.setOnClickListener {

            startActivity(Intent(this, RootActivity::class.java))

        }

        binding.btnOrdered.setOnClickListener{

            startActivity(Intent(this, RootActivity::class.java))

        }

    }
}