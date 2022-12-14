package com.example.foodie.ui.foodInfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.foodie.R
import com.example.foodie.databinding.FragmentFoodInfoBinding
import com.example.foodie.db.DataBase
import com.example.foodie.model.MenuModel

class FoodInfoFragment : Fragment() {

    private lateinit var binding: FragmentFoodInfoBinding

    private val args: FoodInfoFragmentArgs by navArgs()

    lateinit var model: FoodInfoViewModel

    lateinit var menu: MenuModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFoodInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        menu = DataBase.getInstance(requireContext()).menuDao().getMenu(args.idFood)

        model = ViewModelProvider(this)[FoodInfoViewModel::class.java]

        binding.rvInfoRecommended.setDivider(
            R.drawable.recycler_view_divider,
            DividerItemDecoration.HORIZONTAL
        )

        model.allMenu.observe(viewLifecycleOwner, Observer { menu ->

            binding.rvInfoRecommended.adapter = FoodInfoAdapter(menu)

        })

        binding.tvPlus.setOnClickListener {
            var inc = binding.tvCount.text.toString().toInt()
            inc++
            binding.tvCount.text = inc.toString()
        }

        binding.tvMinus.setOnClickListener {
            var dec = binding.tvCount.text.toString().toInt()
            if (dec>1){
                dec--
            }
            binding.tvCount.text = dec.toString()
        }

        binding.icToolbarFoodInfo.back.isVisible = true

        binding.icToolbarFoodInfo.back.setOnClickListener {



        }

        binding.imgInfo.setImageResource(R.drawable.food_image)

        binding.tvInfoName.text = menu.name

        binding.tvInfoPrice.text = "${menu.price} somon"

        binding.tvInfoDescription.text = menu.desc

        binding.tvInfoTotal.text = "Total: ${menu.price}"


    }

}