package com.example.foodie.ui.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.foodie.R
import com.example.foodie.ui.signInUp.SignInOutViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ViewPagerAndTabLayoutFragment : Fragment() {

    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_pager_and_tab_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tabLayout = view.findViewById (R.id.menu_tab_layout)
        viewPager = view.findViewById (R.id.menu_view_pager)
        viewPager.adapter = MenuViewPagerAdapter(requireActivity())

        TabLayoutMediator(tabLayout, viewPager){
                tab, pos ->
            when(pos){
                0 -> {
                    tab.setText("Meals")
                }
                1 -> {
                    tab.setText("Sides")
                }
                2 -> {
                    tab.setText("Snacks")
                }
            }
        }.attach()
    }

}