package com.example.logo.ui.category.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.logo.R
import com.example.logo.data.modelProductList.DataProductList
import com.example.logo.databinding.ItemNewClothesBinding

class CategoryAdapter(private val data: List<DataProductList>)
    : Adapter<CategoryAdapter.CategoryViewHolder>() {

    class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ItemNewClothesBinding.bind(itemView)

        var tvName = binding.textViewName
        var tvPrice = binding.textViewPrice
        var ivNewClothes = binding.imageViewNewClothes
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_catalog, parent, false)

        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val itemsViewModel = data?.get(position)
        holder.tvName.text = itemsViewModel?.name
        holder.tvPrice.text = itemsViewModel?.price + " ₽"
    }

    override fun getItemCount(): Int {
        return data.size
    }
}