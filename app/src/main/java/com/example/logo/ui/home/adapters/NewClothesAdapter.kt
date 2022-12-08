package com.example.logo.ui.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.example.logo.Constant.BASE_URL
import com.example.logo.R
import com.example.logo.data.modelMainPage.NewProduct
import com.example.logo.databinding.ItemBigCardBinding

class NewClothesAdapter(private val mList: List<NewProduct>, private val listener: Listener)
    :Adapter<NewClothesAdapter.NewClothesViewHolder>() {

    class NewClothesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ItemBigCardBinding.bind(itemView)


        fun bind(listener: Listener, mList: List<NewProduct>) = with(binding){
            itemView.setOnClickListener {
                listener.onItemClick(
                    mList?.get(position)?.slug
                )
            }

        }

        var tvName = binding.tvName
        var tvPrice = binding.tvPrice
        var ivNewClothes = binding.imageView

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewClothesViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_big_card, parent, false)

        return NewClothesViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewClothesViewHolder, position: Int) {

        val itemsViewModel = mList?.get(position)
        val i = itemsViewModel!!.images

        holder.bind(listener, mList)
        holder.tvName.text = itemsViewModel?.name
        holder.tvPrice.text = itemsViewModel?.price + " ₽"

        if (itemsViewModel!!.images.isNotEmpty())
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
        return mList.size
    }
}