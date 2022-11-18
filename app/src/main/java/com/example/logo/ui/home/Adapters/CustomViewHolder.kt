package com.example.logo.ui.home.Adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.logo.databinding.CardViewDesignBinding

class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = CardViewDesignBinding.bind(itemView)

    val imageView = binding.imageview
    val textView = binding.textView
}