package com.example.foodie.ui.menu

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.foodie.R
import com.example.foodie.databinding.FragmentMenuBinding
import com.example.foodie.model.MenuModel
import com.example.foodie.model.callback.RecyclerViewItemClick
import com.example.foodie.ui.menu.foodInfo.FoodInfoFragmentArgs

class MenuFragment : Fragment() {

    private var _binding: FragmentMenuBinding? = null

    lateinit var model: MenuViewModel

    private lateinit var sharedViewModel: SharedViewModel

    private val binding get() = _binding!!

    private var category: Int? = null

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
                model.update(item, category ?: 1)
                val userId = requireContext().getSharedPreferences("prefs", Context.MODE_PRIVATE)
                    .getInt("user_id", -1)

                val user = model.db.userDao().getUserById(userId)
                if (user.favoriteList.contains(item.id)) {
                    user.favoriteList.remove(item.id)
                } else {
                    user.favoriteList.add(item.id)
                }
                model.updateFavorites(user)
            }
        }

        override fun onShoppingCartClick(item: Any) {
            TODO("Not yet implemented")
        }
    }

    companion object {

        fun newInstance(category: Int): MenuFragment {
            val fragment = MenuFragment()
            fragment.category = category
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]

        binding.rvMenu.layoutManager = GridLayoutManager(requireContext(), 2)

        model = ViewModelProvider(this)[MenuViewModel::class.java]

        // if (category != null) category!! else 1
        model.initArgs(category ?: 1)

        model.allMenu.observe(viewLifecycleOwner) { menu ->
            val userId = requireContext().getSharedPreferences("prefs", Context.MODE_PRIVATE)
                .getInt("user_id", -1)
            val user = model.db.userDao().getUserById(userId)

            binding.rvMenu.adapter = MenuAdapter(menu, callback, user.favoriteList)
        }

        sharedViewModel.onFavoriteUpdate.observe(viewLifecycleOwner) {
            model.initArgs(category!!)
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}