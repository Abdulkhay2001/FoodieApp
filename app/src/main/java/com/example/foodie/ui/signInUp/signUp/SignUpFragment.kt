package com.example.foodie.ui.signInUp.signUp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.foodie.R
import com.example.foodie.databinding.FragmentSignUpBinding
import com.example.foodie.model.UserModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding

    private var callback: View.OnClickListener? = null

    lateinit var model: SignUpViewModel

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

        model = ViewModelProvider(this)[SignUpViewModel::class.java]

        binding.tvLogin.setOnClickListener {
            callback?.onClick(it)
        }

        binding.signUpBtn.setOnClickListener {
            callback?.onClick(it)

            model.send(
                binding.signUpNameEditText.text.toString(),
                binding.signUpEmailEditText.text.toString(),
                binding.signUpPassEditText.text.toString()
            )

            binding.signUpNameEditText.setText("")
            binding.signUpEmailEditText.setText("")
            binding.signUpPassEditText.setText("")

        }

        model.showDialog.observe(viewLifecycleOwner) {
            MaterialAlertDialogBuilder(requireActivity())
                .setTitle(getString(R.string.app_name))
                .setMessage(it)
                .setNegativeButton("OK", null)
                .show()
        }


    }
}