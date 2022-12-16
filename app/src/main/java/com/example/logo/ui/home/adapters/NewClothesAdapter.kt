package com.example.logo.ui.home.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
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


        fun bind(listener: Listener, mList: List<NewProduct>, btn: ImageView) = with(binding){
            itemView.setOnClickListener {
                listener.onItemClick(
                    mList?.get(position)?.slug
                )
            }

            btn.setOnClickListener {
                listener.showSize(
                    mList[position].name
                )
            }
        }

        var tvName = binding.tvName
        var tvPrice = binding.tvPrice
        var ivNewClothes = binding.imageView
        val addToCart = binding.addToCart

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewClothesViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_big_card, parent, false)

        return NewClothesViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewClothesViewHolder, position: Int) {

        val itemsViewModel = mList?.get(position)
        val i = itemsViewModel!!.images
        val btn = holder.addToCart

        holder.bind(listener, mList, btn)
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
        fun showSize(name : String)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

//    private fun showSizes(name : String){
//
//        val bundle = Bundle()
//        bundle.putString("key", name)
//
//        val bottomSheet = ChooseSize()
//        bottomSheet.show(, "")
//        bottomSheet.arguments = bundle
//    }
}