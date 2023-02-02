package com.example.foodie.ui.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.foodie.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        lifecycleScope.launch{
            delay(3000)
            if (getSharedPreferences("prefs", Context.MODE_PRIVATE).getInt("user_id", -1) == -1){
                val intent = Intent(this@SplashActivity, SignInUpActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                startActivity(Intent(this@SplashActivity, RootActivity::class.java))
            }
        }

    }
}