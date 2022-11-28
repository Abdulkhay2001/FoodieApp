package com.example.foodie.ui.signInUp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.foodie.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding

    private var callback: View.OnClickListener? = null

    companion object {

        fun newInstance(callback: View.OnClickListener): SignUpFragment {
            val fragment = SignUpFragment()
            fragment.callback = callback
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvLogin.setOnClickListener {
            callback?.onClick(it)
        }

        binding.signUpBtn.setOnClickListener {
            callback?.onClick(it)
            //todo добавить регистрацию
        }

    }
}