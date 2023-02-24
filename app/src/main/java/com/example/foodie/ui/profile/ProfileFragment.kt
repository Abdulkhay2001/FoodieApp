package com.example.foodie.ui.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.foodie.R
import com.example.foodie.databinding.FragmentProfileBinding
import com.example.foodie.ui.activity.SignInUpActivity

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    lateinit var model: ProfileViewModel
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        model =
            ViewModelProvider(this)[ProfileViewModel::class.java]
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvExitProfile.setOnClickListener {
            startActivity(Intent(requireContext(), SignInUpActivity::class.java))
            requireContext().getSharedPreferences("prefs", Context.MODE_PRIVATE).edit()
                .putInt("user_id", -1).apply()
        }
 
        model.user.observe(viewLifecycleOwner) {
            binding.tvNameProfile.text = it.name
            binding.tvEmailProfile.text = it.email
        }

        binding.tvPersonalProfile.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_profile_to_profileInformationFragment)
        }

        binding.tvHelpProfile.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_profile_to_helpFragment)
        }

        binding.tvSettingsProfile.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_profile_to_settingsFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}