package com.example.logo.ui.goods

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.forEach
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.logo.Constant.BASE_URL
import com.example.logo.R
import com.example.logo.databinding.FragmentGoodsBinding
import com.example.logo.ui.home.HomeViewModel
import com.google.android.material.chip.Chip

class GoodsFragment: Fragment() {

    companion object{
        const val KEY_NAME = "NAME"

        fun showToast(context: Context, string: Any){
            Toast.makeText(context, "$string", Toast.LENGTH_SHORT).show()
        }
    }

    private var _binding: FragmentGoodsBinding? = null
    private val binding get() = _binding!!

    private var viewModel: HomeViewModel = HomeViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentGoodsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val slug = arguments?.get(KEY_NAME) as String
        Log.d("test", "Передано имя: $slug")

        viewModel.getProductDetails(slug)

        viewModel.productDetailsLiveData.observe(viewLifecycleOwner){

            with(binding){

                textViewBrend.text = it.slug
                textCategory.text = it.category.name
                textViewName.text = it.name
                textViewPrice.text = it.price
                textViewDescriptionText.text = it.description
                textViewLabelNew.apply {
                    visibility = View.VISIBLE
                    if (it.isSale) text = "Sale"

                }
                if (it.images.isNotEmpty()) {

                    Glide.with(binding.imageView)
                        .load(BASE_URL + it.images.get(0).path)
                        .into(binding.imageView)
                }
                else binding.imageView.setImageResource(R.drawable.jesus)

                buttonAddToCard.setOnClickListener {
                    addToCart()
                }
                buttonBack.setOnClickListener {
                    goBack()
                }

                chipGroupChooseColor.apply {

                    it.colors.forEach {
                        val chip = Chip(this.context)

                        chip.text= it.colorName
                        chip.isClickable = true
                        chip.isCheckable = true

                        chip.setOnClickListener{
                            showToast(requireContext(), chip.text)
                        }

                        this.addView(chip)
                    }

                    this.isSelectionRequired = true
                    this.isSingleSelection = true
                }

                chipGroupChooseSize.apply {

                    it.sizes.forEach {
                        val chip = Chip(this.context)

                        chip.text= it.sizeName
                        chip.isClickable = true
                        chip.isCheckable = true
                        this.addView(chip)

                    }
                    this.isSelectionRequired = true
                    this.isSingleSelection = true
                }



            }

       }



    }

    private fun addToCart()
    {

//        showToast(requireContext(), (binding.chipGroupChooseSize.checkedChipId))

            // TODO дописать логику добавления в корзину
    }

    private fun goBack(){
        findNavController().popBackStack()
    }
}