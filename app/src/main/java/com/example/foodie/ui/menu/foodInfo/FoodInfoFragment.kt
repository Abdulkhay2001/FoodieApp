package com.example.foodie.ui.menu.foodInfo

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.transition.Transition
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.foodie.R
import com.example.foodie.databinding.FragmentFoodInfoBinding
import com.example.foodie.model.MenuModel
import com.example.foodie.model.ShoppingCartModel
import com.example.foodie.model.callback.RecyclerViewItemClick
import com.example.foodie.ui.activity.OrderedActivity
import com.example.foodie.ui.menu.SharedViewModel
import com.example.foodie.ui.shoppingCart.ShoppingCartActivity
import kotlin.math.log

class FoodInfoFragment : Fragment() {

    private lateinit var binding: FragmentFoodInfoBinding

    private val args: FoodInfoFragmentArgs by navArgs()

    lateinit var model: FoodInfoViewModel

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
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFoodInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model = ViewModelProvider(this)[FoodInfoViewModel::class.java]
        model.initArgs(args.idFood)

        binding.rvInfoRecommended.setDivider(
            R.drawable.recycler_view_divider,
            DividerItemDecoration.HORIZONTAL
        )

        model.allMenu.observe(viewLifecycleOwner, Observer { menu ->
            binding.rvInfoRecommended.adapter = FoodInfoAdapter(menu, callback)
        })

        binding.tvPlus.setOnClickListener {
            var inc = binding.tvCount.text.toString().toInt()
            inc++
            binding.tvCount.text = inc.toString()
            binding.tvInfoTotal.text = "Total: ${model.menu.value!!.price * inc} TJS"
        }

        binding.tvMinus.setOnClickListener {
            var dec = binding.tvCount.text.toString().toInt()
            if (dec > 1) {
                dec--
            }
            binding.tvCount.text = dec.toString()
            binding.tvInfoTotal.text = "Total: ${model.menu.value!!.price * dec} TJS"
        }

        binding.icToolbarFoodInfo.back.isVisible = true

        binding.icToolbarFoodInfo.back.setOnClickListener {

            findNavController().popBackStack()

        }

        binding.imgInfo.setImageResource(R.drawable.food_image)

        model.menu.observe(viewLifecycleOwner) {
            binding.tvInfoName.text = it.name

            binding.tvInfoPrice.text = "${it.price} TJS"

            binding.tvInfoDescription.text = it.desc

            binding.tvInfoTotal.text = "Total: ${it.price} TJS"
        }

        binding.buyBtb.setOnClickListener {


            startActivity(Intent(requireContext(), OrderedActivity::class.java))

        }

        binding.btnAddToCart.setOnClickListener {

            val userId = requireContext().getSharedPreferences("prefs", Context.MODE_PRIVATE)
                .getInt("user_id", -1)

            val count = binding.tvCount.text.toString().toInt()

            val sh = model.shoppingCart.value!!.firstOrNull { it.menuId == args.idFood }

            if (model.db.shoppingCartDao()
                    .checkShoppingCart(model.menu.value!!.id, userId) == null
            ) {
                model.insert(userId, model.menu.value!!.id, count)
            } else {
                sh!!.count += 1
                model.update(sh!!)
            }

            Toast.makeText(requireContext(), "Added to shopping cart", Toast.LENGTH_SHORT).show()

        }



        binding.icToolbarFoodInfo.imgShoppingCart.setOnClickListener {
            val intent = Intent(requireContext(), ShoppingCartActivity::class.java)
            startActivity(intent)

        }


    }


}