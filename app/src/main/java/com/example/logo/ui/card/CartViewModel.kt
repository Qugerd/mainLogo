package com.example.logo.ui.card

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.ListAdapter
import com.example.logo.data.modelProductDetails.Data
import com.example.logo.databinding.FragmentCardBinding
import com.example.logo.databinding.FragmentGoodsBinding

class CartViewModel: ViewModel() {

    private var _binding: FragmentCardBinding? = null
    private val binding get() = _binding!!

//    private val _cartList = MutableLiveData<>()
//    val cartList: LiveData<> = _cartList

}