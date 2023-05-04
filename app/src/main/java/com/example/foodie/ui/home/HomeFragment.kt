package com.example.foodie.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.foodie.R
import com.example.foodie.databinding.FragmentHomeBinding
import com.example.foodie.model.MenuModel
import com.example.foodie.model.callback.RecyclerViewItemClick
import com.example.foodie.ui.menu.foodInfo.FoodInfoFragmentArgs
import com.example.foodie.ui.shoppingCart.ShoppingCartActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    lateinit var model: HomeViewModel

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
            TODO("Not yet implemented")
        }

        override fun onShoppingCartClick(item: Any) {
            if (item is MenuModel) {
                Log.d("TAG", "insert")
//                model.update(item)
                val userId = requireContext().getSharedPreferences("prefs", Context.MODE_PRIVATE)
                    .getInt("user_id", -1)

                val sh = model.shoppingCart.value!!.firstOrNull {
                    it.menuId == item.id }


                if (model.db.shoppingCartDao()
                        .checkShoppingCart(item.id, userId) == null
                ) {
                    model.insert(userId, item.id, 1)

                } else {
                    sh!!.count += 1
                    model.update(sh!!)
                }

                Toast.makeText(requireContext(), "Added to shopping cart", Toast.LENGTH_SHORT)
                    .show()

            }

        }

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model = ViewModelProvider(this)[HomeViewModel::class.java]

        model.allMenu.observe(viewLifecycleOwner) {

            binding.rvHomeRecommend.adapter = HomeRecommendAdapter(it, callback)
            binding.rvHomePopular.adapter = HomeNewAdapter(it, callback)
            binding.rvHomeNew.adapter = HomeNewAdapter(it, callback)

        }



        binding.icHome.tvRoot.text = "Home"
        binding.icHome.imgShoppingCart.setOnClickListener {
            startActivity(Intent(requireContext(), ShoppingCartActivity::class.java))
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}