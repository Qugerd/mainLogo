package com.example.logo.ui.category.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.logo.Constant.BASE_URL
import com.example.logo.Constant.RUB_SIMBOL
import com.example.logo.R
import com.example.logo.data.modelProductList.DataProductList
import com.example.logo.data.post.modification.Modification
import com.example.logo.databinding.ItemCatalogBinding
import com.example.logo.ui.goods.GoodsFragment.Companion.mList
import com.example.logo.ui.home.adapters.NewClothesAdapter

class CategoryAdapter(private val data: List<DataProductList>, private val listener: NewClothesAdapter.Listener)
    : Adapter<CategoryAdapter.CategoryViewHolder>() {

    class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ItemCatalogBinding.bind(itemView)

        fun bind(listener: NewClothesAdapter.Listener, mList: List<DataProductList>) = with(binding){
            itemView.setOnClickListener {
                listener.onItemClick(
                    mList?.get(position)?.slug
                )
            }

            btnAddToCard.setOnClickListener {
                listener.showSize(
                    mList[position].name
                )
            }
        }

        val tvName = binding.tvName
        val tvPrice = binding.tvPrice
        val imSlider = binding.imSlider
        val btnAddToCard = binding.btnAddToCard
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_catalog, parent, false)

        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {

        val itemsViewModel = data?.get(position)
        val btnAddToCard = holder.btnAddToCard

        checkInCart(btnAddToCard, position)

        holder.bind(listener, data)
        holder.tvName.text = itemsViewModel?.name
        holder.tvPrice.text = itemsViewModel?.price + RUB_SIMBOL


        val imageList = ArrayList<SlideModel>()
        itemsViewModel?.images?.forEach {
            imageList.add(SlideModel(BASE_URL + it.path, ScaleTypes.CENTER_CROP))
        }
        holder.imSlider.setImageList(imageList)




    }

    private fun checkInCart(btnAddToCard: ImageView, position: Int) {
        data[position].apply {
            if (mList.contains(Modification(this.id.toInt(), 1))){
                Glide.with(btnAddToCard)
                    .load(R.drawable.ic_cart__on)
                    .error(R.drawable.ic_cart__on)
                    .into(btnAddToCard)
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}