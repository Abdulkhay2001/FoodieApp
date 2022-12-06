package com.example.foodie.ui.signInUp

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.foodie.ui.signInUp.signIn.SignInFragment
import com.example.foodie.ui.signInUp.signUp.SignUpFragment

class SignInOutViewPagerAdapter(fragmentActivity: FragmentActivity, private val loginBtnCallback: View.OnClickListener) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> SignInFragment()
            else -> SignUpFragment.newInstance(loginBtnCallback)
        }
    }
    
}