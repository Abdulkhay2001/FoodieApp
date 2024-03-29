package com.example.foodie.ui.signInUp.signIn

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.foodie.R
import com.example.foodie.databinding.FragmentSignInBinding
import com.example.foodie.ui.activity.RootActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class SignInFragment : Fragment() {

    lateinit var model: SignInViewModel
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

        model = ViewModelProvider(this)[SignInViewModel::class.java]

        binding.signInBtn.setOnClickListener {
            model.send(binding.emailEditText.text.toString(), binding.passEditText.text.toString())
        }

        model.showDialog.observe(viewLifecycleOwner) {
            MaterialAlertDialogBuilder(requireActivity())
                .setTitle(getString(R.string.app_name))
                .setMessage(it)
                .setNegativeButton("OK", null)
                .show()
        }

        model.open.observe(viewLifecycleOwner) {
            requireContext().getSharedPreferences("prefs", Context.MODE_PRIVATE).edit().putInt("user_id", it.id).apply()
            requireActivity().run {
                startActivity(Intent(this, RootActivity::class.java))
                finish()
            }
        }

    }

    override fun onResume() {
        super.onResume()
        model.getAllUser()
    }

}