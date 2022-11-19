package com.example.logo.ui.home.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.logo.R
import com.example.logo.data.model.Category
import com.example.logo.data.model.Data

class CustomAdapter(private val data: List<Data>?)
    :Adapter<CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)

        return CustomViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        val ItemsViewModel = data?.get(position)
//        holder.imageView.setImageResource(ItemsViewModel.image)
        holder.textView.text = ItemsViewModel?.name

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return data!!.size
    }
}