package com.example.foodie.ui.menu.foodInfo

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import com.example.foodie.model.callback.RecyclerViewItemClick
import com.example.foodie.ui.activity.OrderedActivity
import com.example.foodie.ui.activity.RootActivity
import com.example.foodie.ui.menu.MenuFragment

class FoodInfoFragment : Fragment() {

    private lateinit var binding: FragmentFoodInfoBinding

    private val args: FoodInfoFragmentArgs by navArgs()

    lateinit var model: FoodInfoViewModel

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
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFoodInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

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
        }

        binding.tvMinus.setOnClickListener {
            var dec = binding.tvCount.text.toString().toInt()
            if (dec > 1) {
                dec--
            }
            binding.tvCount.text = dec.toString()
        }

        binding.icToolbarFoodInfo.back.isVisible = true

        binding.icToolbarFoodInfo.back.setOnClickListener {

            findNavController().popBackStack()

        }

        binding.imgInfo.setImageResource(R.drawable.food_image)

        model.menu.observe(viewLifecycleOwner) {
            binding.tvInfoName.text = it.name

            binding.tvInfoPrice.text = "${it.price} somon"

            binding.tvInfoDescription.text = it.desc

            binding.tvInfoTotal.text = "Total: ${it.price}"
        }

        binding.buyBtb.setOnClickListener {


                startActivity(Intent(requireContext(), OrderedActivity::class.java))


        }


    }

}