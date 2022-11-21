package com.example.logo.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.example.logo.R
import com.example.logo.data.modelProductList.Data

class NewClothesAdapter(private val data: List<Data>?)
    :Adapter<NewClothesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewClothesViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_new_clothes, parent, false)

        return NewClothesViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewClothesViewHolder, position: Int) {

        val BASE_URL = "http://10.0.2.2:80"

        val itemsViewModel = data?.get(position)
        val i = itemsViewModel!!.images

        holder.tvName.text = itemsViewModel?.name
        holder.tvPrice.text = itemsViewModel?.price + " ₽"


        if (itemsViewModel.images.isNotEmpty())
        {
            Glide.with(holder.ivNewClothes)
                .load(BASE_URL + i.get(0).path)
                .into(holder.ivNewClothes)
        }
        else holder.ivNewClothes.setImageResource(R.drawable.new_clothes)
    }

    override fun getItemCount(): Int {
        return data!!.size
    }
}