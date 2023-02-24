package com.example.foodie.ui.profile.profileInformation

import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings.TextSize
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.foodie.databinding.FragmentProfileInformationBinding

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
//        binding.icPersonalInformation.tvRoot.setTextSize(TypedValue.COMPLEX_UNIT_SP, 32f)
        binding.icPersonalInformation.back.setOnClickListener {
            findNavController().popBackStack()
        }

        model.user.observe(viewLifecycleOwner){
            binding.tvNameProfileInformation.text = it.name
            binding.tvMailProfileInformation.text = it.email
        }
    }


}