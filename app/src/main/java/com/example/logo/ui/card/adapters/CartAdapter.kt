package com.example.logo.ui.card.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.example.logo.Constant
import com.example.logo.R
import com.example.logo.data.modelCart.Item
import com.example.logo.databinding.ItemCartBinding

class CartAdapter(private val mList: List<Item>): Adapter<CartAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val binding = ItemCartBinding.bind(itemView)

        val tvName = binding.tvName
        val tvSize = binding.tvSize
        val tvColor = binding.tvColor
        val tvBrend = binding.tvBrend
        val tvCategory = binding.tvCategory
        val tvPrice = binding.tvPrice
        val tvOldPrice = binding.tvOldPrice
        val tvLast = binding.tvLast
        val imageView = binding.imageView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cart, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val iView = mList[position]

        with(holder){
            tvName.text = iView.product.name
            tvBrend.text = iView.product.brand.brandName
            tvCategory.text = iView.product.category.name
            tvPrice.text = iView.product.discountPrice
            tvOldPrice.text = iView.product.price
            tvColor.text = iView.product.colors[0].colorName
            tvSize.text = iView.product.sizes[0].sizeName

            if (iView.product.images.isNotEmpty())
            {
                Glide.with(holder.imageView)
                    .load(Constant.BASE_URL + iView.product.images[0].path)
                    .into(holder.imageView)
            }
            else holder.imageView.setImageResource(R.drawable.jesus)
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}