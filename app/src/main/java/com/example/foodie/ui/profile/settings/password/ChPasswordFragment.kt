package com.example.foodie.ui.profile.settings.password

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.foodie.R
import com.example.foodie.databinding.FragmentChPasswordBinding
import com.example.foodie.databinding.FragmentOldPasswordBinding

class ChPasswordFragment : Fragment() {

    private var _binding: FragmentChPasswordBinding? = null
    private val binding get() = _binding!!
    lateinit var model: ChPasswordViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentChPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model = ViewModelProvider(this)[ChPasswordViewModel::class.java]

        binding.icNewPass.imgShoppingCart.isVisible = false
        binding.icNewPass.back.isVisible = true
        binding.icNewPass.back.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.icNewPass.tvRoot.text = "Change password"

        binding.btnNewPass.setOnClickListener {
            if (binding.newPassEditText.text.toString() == binding.newPassEditText2.text.toString()){
                model.user.value!!.password = binding.newPassEditText.text.toString()
                model.update(model.user.value!!)
                findNavController().navigate(R.id.action_chPasswordFragment_to_navigation_profile)
            }else{
                Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
            }
        }

    }

}
