package com.example.logo.ui.home.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.logo.databinding.ItemNewClothesBinding

class NewClothesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemNewClothesBinding.bind(itemView)

    var tvName = binding.textViewName
    var tvPrice = binding.textViewPrice
    var tvLabelNew = binding.textViewLabelNew
    var ivNewClothes = binding.imageViewNewClothes
}