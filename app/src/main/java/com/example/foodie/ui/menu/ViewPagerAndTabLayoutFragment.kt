package com.example.foodie.ui.menu

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.foodie.R
import com.example.foodie.databinding.FragmentMenuBinding
import com.example.foodie.databinding.FragmentViewPagerAndTabLayoutBinding
import com.example.foodie.ui.shoppingCart.ShoppingCartActivity
import com.example.foodie.ui.signInUp.SignInOutViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ViewPagerAndTabLayoutFragment : Fragment() {

    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager2

    private var _binding: FragmentViewPagerAndTabLayoutBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentViewPagerAndTabLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.menuViewPager.adapter = MenuViewPagerAdapter(requireActivity())

        TabLayoutMediator(binding.menuTabLayout, binding.menuViewPager){
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

        binding.icToolbar.imgShoppingCart.setOnClickListener {

            startActivity(Intent(requireContext(),ShoppingCartActivity::class.java))
        }


    }

}