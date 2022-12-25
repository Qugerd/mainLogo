package com.example.logo.ui.search.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.logo.R
import com.example.logo.databinding.ItemSearchInputBinding

class SearchHistoryAdapter(var mCtx:Context, var items:List<String>)
    : ArrayAdapter<String>( mCtx , 0 , items ){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val view = LayoutInflater.from(parent?.context)
            .inflate(R.layout.item_list_search, parent, false)

        val textView : TextView = view.findViewById(R.id.tv)

        val itemView = items[position]
        textView.text = itemView
        return view
    }
}
