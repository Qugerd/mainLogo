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
import com.example.logo.data.post.modification.Modification
import com.example.logo.databinding.ItemBigCardBinding
import com.example.logo.ui.goods.GoodsFragment

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
                    mList[position].slug
                )
//                Glide.with(addToCart)
//                    .load(R.drawable.ic_cart__on)
//                    .error(R.drawable.ic_cart__on)
//                    .into(addToCart)
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
        val addToCart = holder.addToCart

        checkInCart(addToCart, position)

        holder.bind(listener, mList, addToCart)
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

    override fun getItemCount(): Int {
        return mList.size
    }

    private fun checkInCart(addToCard: ImageView, position: Int) {
        mList[position].apply {
            if (GoodsFragment.mList.contains(Modification(this.id.toInt(),1,1, 1))){
                Glide.with(addToCard)
                    .load(R.drawable.ic_cart__on)
                    .error(R.drawable.ic_cart__on)
                    .into(addToCard)
            }
        }
    }

    interface Listener{
        fun onItemClick(position: String?)
        fun showSize(name : String)
    }



}