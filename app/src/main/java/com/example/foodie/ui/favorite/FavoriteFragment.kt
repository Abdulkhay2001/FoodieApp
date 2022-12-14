package com.example.foodie.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.foodie.R
import com.example.foodie.databinding.FragmentFavoriteBinding
import com.example.foodie.databinding.FragmentMenuBinding
import com.example.foodie.model.MenuModel
import com.example.foodie.model.callback.RecyclerViewItemClick
import com.example.foodie.ui.menu.MenuViewModel
import com.example.foodie.ui.menu.SharedViewModel
import com.example.foodie.ui.menu.foodInfo.FoodInfoFragmentArgs

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    lateinit var model: FavoriteViewModel
    private lateinit var sharedViewModel: SharedViewModel

    private val callback = object : RecyclerViewItemClick {
        override fun onItemClickCallback(item: Any) {
            if (item is MenuModel) {
                findNavController().navigate(
                    R.id.foodInfoFragment,
                    FoodInfoFragmentArgs(item.id).toBundle()
                )
            }
        }

        override fun onFavoriteClick(item: Any) {
            if (item is MenuModel) {
                item.favorite = item.favorite.not()
                model.update(item)
                sharedViewModel.onFavoriteBtnClick()
            }
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]

        binding.rvFavorite.layoutManager = GridLayoutManager(requireContext(), 2)

        model = ViewModelProvider(this)[FavoriteViewModel::class.java]

        model.favorite.observe(viewLifecycleOwner) { favorite ->

            binding.rvFavorite.adapter = FavoriteAdapter(favorite, callback)

        }

        binding.icToolbar.tvRoot.text = "Favorite"

    }

    override fun onResume() {
        super.onResume()
        model.getFavorite()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}