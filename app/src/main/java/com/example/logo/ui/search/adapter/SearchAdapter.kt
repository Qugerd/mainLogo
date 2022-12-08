package com.example.logo.ui.search.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.logo.R
import com.example.logo.data.modelProductList.DataProductList
import com.example.logo.databinding.FragmentSearchBinding
import com.example.logo.databinding.ItemSearchInputBinding

class SearchAdapter(private val mList: List<DataProductList>) : Adapter<SearchAdapter.SearchViewHolder>() {
    class SearchViewHolder(view: View) : RecyclerView.ViewHolder(view){

        private val binding = ItemSearchInputBinding.bind(itemView)

        val tvName = binding.tvName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_search_input, parent, false)

        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {

        val itemView = mList.get(position)
        holder.tvName.text = itemView.name
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}