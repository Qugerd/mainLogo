package com.example.logo.ui.goods

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Paint
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
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.logo.Constant.BASE_URL
import com.example.logo.Constant.RUB_SIMBOL
import com.example.logo.R
import com.example.logo.bottom_sheets.ChooseSize
import com.example.logo.bottom_sheets.RegistrationBottomSheet
import com.example.logo.bottom_sheets.TableSize
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

                tvName.text = "${it.name.capitalize()} ${it.category.name.capitalize()} ${it.slug.capitalize()}"
                tvPriceNew.text = it.price
                textViewDescriptionText.text = it.description
                tvPrice.apply {
                    paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                    text = it.discountPrice + RUB_SIMBOL
                }
                if (it.isNew) textViewLabelNew.visibility = View.VISIBLE
                if (it.isSale) textViewLabelSale.visibility = View.VISIBLE


                val imageList = ArrayList<SlideModel>()
                it.images.forEach {
                    imageList.add(SlideModel(BASE_URL + it.path, ScaleTypes.CENTER_CROP))
                }
                imageView.setImageList(imageList)



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



                textViewTableSize.setOnClickListener {
                    showTableSize()
                }

            }

       }



    }

    fun showTableSize(){
        val bottomSheet = TableSize()
        bottomSheet.isCancelable = false
        bottomSheet.show(parentFragmentManager, "")
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