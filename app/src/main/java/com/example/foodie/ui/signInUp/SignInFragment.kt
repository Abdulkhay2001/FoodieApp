package com.example.foodie.ui.signInUp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.foodie.R
import com.example.foodie.databinding.FragmentSignInBinding
import com.example.foodie.databinding.FragmentSignUpBinding
import com.example.foodie.ui.RootActivity

class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSignInBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //todo добавить проверку login and password
        binding.signInBtn.setOnClickListener {
            requireActivity().run{
                startActivity(Intent(this, RootActivity::class.java))
                finish()
            }
        }

    }

}