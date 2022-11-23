package com.example.logo.ui.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.example.logo.R
import com.example.logo.data.modelProductList.Data
import com.example.logo.databinding.ItemCardSalesBinding

class SalesAdapter(private val data: List<Data>?, private val listener: NewClothesAdapter.Listener)
    :Adapter<SalesAdapter.SalesViewHolder>() {

    class SalesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ItemCardSalesBinding.bind(itemView)

        fun bind(listener: NewClothesAdapter.Listener, data: List<Data>?) = with(binding){
            itemView.setOnClickListener {
                listener.onItemClick(
                    data?.get(position)?.slug
                )
            }
        }

        var tvName = binding.textViewName
        var tvPrice = binding.textViewPrice
        var tvLabelNew = binding.textViewLabelNew
        var ivNewClothes = binding.imageViewNewClothes
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SalesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_card_sales, parent, false)

        return SalesViewHolder(view)
    }

    override fun onBindViewHolder(holder: SalesViewHolder, position: Int) {

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

    override fun getItemCount(): Int {
        return data!!.size
    }
}