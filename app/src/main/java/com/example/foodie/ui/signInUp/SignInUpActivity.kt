package com.example.foodie.ui.signInUp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.foodie.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class SignInUpActivity : AppCompatActivity() {

    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in_up)

        tabLayout = findViewById (R.id.sign_tab_layout)
        viewPager = findViewById (R.id.sign_view_pager)
        viewPager.adapter = ViewPagerAdapter(this as FragmentActivity) {
            viewPager.currentItem = 0
        }

        //отключает переход по свайпу viewPager2
        viewPager.isUserInputEnabled = false

        TabLayoutMediator(tabLayout, viewPager){
            tab, pos ->
            when(pos){
                0 -> {
                    tab.setText("SIGN-IN")
                }
                1 -> {
                    tab.setText("SIGN-UP")
                }
            }
        }.attach()

    }
}