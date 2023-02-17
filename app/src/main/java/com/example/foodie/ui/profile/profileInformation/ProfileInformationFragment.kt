package com.example.foodie.ui.profile.profileInformation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.foodie.R
import com.example.foodie.databinding.FragmentProfileBinding
import com.example.foodie.databinding.FragmentProfileInformationBinding
import com.example.foodie.ui.profile.ProfileViewModel

class ProfileInformationFragment : Fragment() {

    private var _binding: FragmentProfileInformationBinding? = null
    lateinit var model: ProfileInformationViewModel

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        model =
            ViewModelProvider(this)[ProfileInformationViewModel::class.java]
        _binding = FragmentProfileInformationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.icPersonalInformation.back.visibility = View.VISIBLE
        binding.icPersonalInformation.imgShoppingCart.visibility = View.GONE
        binding.icPersonalInformation.tvRoot.text = "Profile information"

        model.user.observe(viewLifecycleOwner){
            binding.tvNameProfileInformation.text = it.name
            binding.tvMailProfileInformation.text = it.email
        }

    }


}