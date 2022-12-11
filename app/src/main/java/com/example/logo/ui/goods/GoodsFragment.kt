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
import androidx.core.os.bundleOf
import androidx.core.view.children
import androidx.core.view.forEach
import androidx.core.view.get
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.models.SlideModel
import com.example.logo.Constant
import com.example.logo.Constant.BASE_URL
import com.example.logo.Constant.RUB_SIMBOL
import com.example.logo.R
import com.example.logo.bottom_sheets.ChooseSize
import com.example.logo.bottom_sheets.RegistrationBottomSheet
import com.example.logo.bottom_sheets.TableSize
import com.example.logo.databinding.FragmentGoodsBinding
import com.example.logo.ui.home.HomeViewModel
import com.google.android.material.chip.Chip

class GoodsFragment() : Fragment(){

    companion object{
        const val KEY_NAME = "NAME"
        const val KEY_POSITION = "POSITION"

        fun showToast(context: Context, string: Any){
            Toast.makeText(context, "$string", Toast.LENGTH_SHORT).show()
        }

       val imagePaths = ArrayList<String>()
    }

    private var _binding: FragmentGoodsBinding? = null
    private val binding get() = _binding!!

    private var viewModel: HomeViewModel = HomeViewModel()
    private val sizeList: ArrayList<String> = arrayListOf()
    private val imageList = ArrayList<SlideModel>()

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
        initView()
    }

    private fun initView() {
        viewModel.productDetailsLiveData.observe(viewLifecycleOwner){

            with(binding){

                tvName.text = "${it.name.capitalize()} ${it.category.name.capitalize()} ${it.slug.capitalize()}"
                tvPriceNew.text = it.discountPrice
                textViewDescriptionText.text = it.description
                tvPrice.apply {
                    paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                    text = it.price + RUB_SIMBOL
                }
                if (it.isNew) textViewLabelNew.visibility = View.VISIBLE
                if (it.isSale) textViewLabelSale.visibility = View.VISIBLE

//                if(imagePaths.isNotEmpty()) imagePaths.clear()


//                val imageList = ArrayList<SlideModel>()
                if (imageList.isEmpty()){
                    it.images.forEach {
                        imageList.add(SlideModel(BASE_URL + it.path, ScaleTypes.CENTER_CROP))
                        imagePaths.add(BASE_URL + it.path)
                    }
                }
                // TODO: Педелать очистку массива, при повторном открытии пустйо массив
                if (imagePaths.isEmpty()){
                    it.images.forEach {
                        imagePaths.add(BASE_URL + it.path)
                    }
                    Log.d("test", "size:${imagePaths.size}")
                }

                imageView.setImageList(imageList)
                imageView.setItemClickListener(object : ItemClickListener {
                    override fun onItemSelected(position: Int) {
                        Toast.makeText(requireContext(), "position $position", Toast.LENGTH_SHORT).show()
                        showGallery(position)
                    }
                })


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
                        sizeList.add(it.sizeName)
                        val chip = Chip(this.context)

                        chip.text = it.sizeName
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

    private fun showGallery(position: Int) {
        findNavController().navigate(R.id.action_goodsFragment_to_gallery,
        bundleOf(KEY_POSITION to position))
    }

    fun showTableSize(){
        val bottomSheet = TableSize()
        bottomSheet.isCancelable = false
        bottomSheet.show(parentFragmentManager, "")
    }


    private fun addToCart()
    {
        if (binding.chipGroupChooseSize.checkedChipId == -1){
            val bundle = Bundle()
            bundle.putStringArrayList("key", sizeList)

            val bottomSheet = ChooseSize()
            bottomSheet.show(childFragmentManager, "")
            bottomSheet.arguments = bundle
        }
    }

    private fun goBack(){
        findNavController().popBackStack()
    }
}