package com.example.logo.ui.goods

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.logo.R
import com.example.logo.databinding.FragmentGoodsBinding
import com.example.logo.ui.home.HomeViewModel

class GoodsFragment: Fragment() {

    companion object{
        const val KEY_NAME = "NAME"
        const val BASE_URL = "http://10.0.2.2:80"
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
                textViewLabelNew.visibility = View.VISIBLE
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


            }

       }



    }

    private fun addToCart()
    {
        Toast.makeText(requireContext(), "Добавлено в карзину", Toast.LENGTH_SHORT).show()
    }

    private fun goBack(){
        findNavController().popBackStack()
    }
}