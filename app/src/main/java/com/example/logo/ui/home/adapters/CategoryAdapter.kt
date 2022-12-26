package com.example.logo.ui.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.logo.Constant
import com.example.logo.R
import com.example.logo.data.modelMainPage.SaleProduct
import com.example.logo.data.modelProductList.DataProductList
import com.example.logo.databinding.ItemCardBinding


class CategoryAdapter(private val mList: List<DataProductList>, private val listener: NewClothesAdapter.Listener)
    : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ItemCardBinding.bind(itemView)


        fun bind(listener: NewClothesAdapter.Listener, mList: List<DataProductList>) = with(binding){
            itemView.setOnClickListener {
                listener.onItemClick(
                    mList?.get(position)?.slug
                )
            }

            btn.setOnClickListener {
                listener.showSize(
                    mList[position].slug
                )
            }
        }

        var tvName = binding.tvName
        var tvPrice = binding.tvPrice
        var ivNewClothes = binding.imageView

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_card, parent, false)

        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {

        val itemsViewModel = mList?.get(position)
        val i = itemsViewModel!!.images

        holder.bind(listener, mList)
        holder.tvName.text = itemsViewModel?.name
        holder.tvPrice.text = itemsViewModel?.price + " ₽"

        if (itemsViewModel!!.images.isNotEmpty())
        {
            Glide.with(holder.ivNewClothes)
                .load(Constant.BASE_URL + i.get(0).path)
                .into(holder.ivNewClothes)
        }
        else holder.ivNewClothes.setImageResource(R.drawable.jesus)
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}