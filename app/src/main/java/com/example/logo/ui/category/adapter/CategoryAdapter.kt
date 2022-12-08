package com.example.logo.ui.category.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.logo.Constant
import com.example.logo.Constant.BASE_URL
import com.example.logo.Constant.RUB_SIMBOL
import com.example.logo.R
import com.example.logo.data.modelProductList.DataProductList
import com.example.logo.databinding.ItemCatalogBinding

class CategoryAdapter(private val data: List<DataProductList>)
    : Adapter<CategoryAdapter.CategoryViewHolder>() {

    class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ItemCatalogBinding.bind(itemView)

        val tvName = binding.tvName
        val tvPrice = binding.tvPrice
        val imSlider = binding.imSlider
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_catalog, parent, false)

        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {

        val itemsViewModel = data?.get(position)


        holder.tvName.text = itemsViewModel?.name
        holder.tvPrice.text = itemsViewModel?.price + RUB_SIMBOL


        val imageList = ArrayList<SlideModel>()
        itemsViewModel?.images?.forEach {
            imageList.add(SlideModel(BASE_URL + it.path, ScaleTypes.CENTER_CROP))
        }
        holder.imSlider.setImageList(imageList)




    }

    override fun getItemCount(): Int {
        return data.size
    }
}