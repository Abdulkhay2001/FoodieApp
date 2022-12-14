package com.example.foodie.ui.menu

import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.foodie.R
import com.example.foodie.databinding.FragmentMenuBinding
import com.example.foodie.model.MenuModel
import com.example.foodie.model.callback.RecyclerViewItemClick
import com.example.foodie.ui.foodInfo.FoodInfoFragment
import com.example.foodie.ui.foodInfo.FoodInfoFragmentArgs

class MenuFragment : Fragment() {

    private var _binding: FragmentMenuBinding? = null

    lateinit var model: MenuViewModel

    private val binding get() = _binding!!

    private val callback = object : RecyclerViewItemClick{
        override fun onItemClickCallback(item: Any) {
            if (item is MenuModel){
                findNavController().navigate(
                    R.id.foodInfoFragment,
                    FoodInfoFragmentArgs(item.id).toBundle()
                )
            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this)[MenuViewModel::class.java]

        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvMenu.layoutManager = GridLayoutManager(requireContext(), 2)

        model = ViewModelProvider(this)[MenuViewModel::class.java]

        model.allMenu.observe(viewLifecycleOwner, Observer { menu ->

            binding.rvMenu.adapter = MenuAdapter(menu, callback)

        })


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}