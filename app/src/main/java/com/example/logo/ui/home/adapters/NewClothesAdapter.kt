package com.example.logo.ui.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.example.logo.R
import com.example.logo.data.modelProductList.DataProductList
import com.example.logo.databinding.ItemNewClothesBinding

class NewClothesAdapter(private val data: List<DataProductList>?, private val listener: Listener)
    :Adapter<NewClothesAdapter.NewClothesViewHolder>() {

    class NewClothesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ItemNewClothesBinding.bind(itemView)


        fun bind(listener: Listener, data: List<DataProductList>?) = with(binding){
            itemView.setOnClickListener {
                listener.onItemClick(
                    data?.get(position)?.slug
                )
            }
        }

        var tvName = binding.textViewName
        var tvPrice = binding.textViewPrice
        var ivNewClothes = binding.imageViewNewClothes

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewClothesViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_new_clothes, parent, false)

        return NewClothesViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewClothesViewHolder, position: Int) {

        val BASE_URL = "http://10.0.2.2:80"

        val itemsViewModel = data?.get(position)
        val i = itemsViewModel!!.images

        holder.bind(listener, data)
        holder.tvName.text = itemsViewModel?.name
        holder.tvPrice.text = itemsViewModel?.price + " ₽"

        if (itemsViewModel.images.isNotEmpty())
        {
            Glide.with(holder.ivNewClothes)
                .load(BASE_URL + i.get(0).path)
                .into(holder.ivNewClothes)
        }
        else holder.ivNewClothes.setImageResource(R.drawable.jesus)
    }

    interface Listener{
        fun onItemClick(position: String?)
    }

    override fun getItemCount(): Int {
        return data!!.size
    }
}