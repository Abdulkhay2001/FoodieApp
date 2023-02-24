package com.example.foodie.ui.profile.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.foodie.R
import com.example.foodie.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.icSettings.back.isVisible = true

        binding.icSettings.back.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.icSettings.imgShoppingCart.isVisible = false
        binding.icSettings.tvRoot.text = "Settings"

        binding.tvChangePass.setOnClickListener {
            findNavController().navigate(R.id.action_settingsFragment_to_oldPasswordFragment)
        }

    }

}