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

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)

        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        val ItemsViewModel = data?.get(position)
        holder.textView.text = ItemsViewModel?.name

    }

    override fun getItemCount(): Int {

        return data!!.size
    }
}