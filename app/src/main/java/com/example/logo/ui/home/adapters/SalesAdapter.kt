package com.example.logo.ui.home.adapters

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.example.logo.Constant.BASE_URL
import com.example.logo.R
import com.example.logo.data.modelMainPage.SaleProduct
import com.example.logo.databinding.ItemBigCardBinding

class SalesAdapter(private val mList: List<SaleProduct>, private val listener: NewClothesAdapter.Listener)
    :Adapter<SalesAdapter.SalesViewHolder>() {

    class SalesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ItemBigCardBinding.bind(itemView)

        fun bind(listener: NewClothesAdapter.Listener, mList: List<SaleProduct>) = with(binding){
            itemView.setOnClickListener {
                listener.onItemClick(
                    mList?.get(position)?.slug
                )
            }
        }

        val tvName = binding.tvName
        val ivNewClothes = binding.imageView
        val tvPrice = binding.tvPrice
        val tvPriceDiscount = binding.tvPriceDiscount
        val tvLabel = binding.tvLabel
        val tvLabelSale = binding.tvLabelSale
        val imView = binding.imView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SalesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_big_card, parent, false)

        return SalesViewHolder(view)
    }

    override fun onBindViewHolder(holder: SalesViewHolder, position: Int) {

        val itemsViewModel = mList?.get(position)
        val i = itemsViewModel!!.images

        holder.bind(listener, mList)

        with(holder){
            tvName.text = itemsViewModel?.name
            tvPrice.text = itemsViewModel?.discountPrice + " ₽"
            tvPriceDiscount.apply {
                paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                text = itemsViewModel?.price + " ₽"
            }
            tvLabel.visibility = View.GONE
            tvLabelSale.visibility = View.VISIBLE
            imView.visibility = View.GONE
        }

        if (itemsViewModel.images.isNotEmpty())
        {
            Glide.with(holder.ivNewClothes)
                .load(BASE_URL + i.get(0).path)
                .into(holder.ivNewClothes)
        }
        else holder.ivNewClothes.setImageResource(R.drawable.jesus)
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}