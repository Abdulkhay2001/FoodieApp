package com.example.foodie.ui.profile.settings.password

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.foodie.R
import com.example.foodie.databinding.FragmentOldPasswordBinding

class OldPasswordFragment : Fragment() {

    private var _binding: FragmentOldPasswordBinding? = null
    private val binding get() = _binding!!
    lateinit var model: OldPassViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOldPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model = ViewModelProvider(this)[OldPassViewModel::class.java]

        binding.icOldPass.back.isVisible = true
        binding.icOldPass.back.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.icOldPass.imgShoppingCart.isVisible = false
        binding.icOldPass.tvRoot.text = "Change password"
        binding.btnOldPass.setOnClickListener {

            model.user.observe(viewLifecycleOwner) { user ->
                val oldPass = binding.oldPassEditText.text.toString()

                if (oldPass == user.password){
                    findNavController().navigate(R.id.action_oldPasswordFragment_to_chPasswordFragment)
                } else{
                    Toast.makeText(requireContext(), "Incorrect password", Toast.LENGTH_SHORT).show()
                }
            }

        }


    }

}