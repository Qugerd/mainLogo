package com.example.logo.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.logo.data.ApiInterface
import com.example.logo.data.model.Data
import com.example.logo.data.model.ProductList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(): ViewModel(){
//   private var _productList = MutableLiveData<ProductList>()
//   val productList: LiveData<ProductList> = _productList
//
//    fun getProductList() {
//        val response = ApiInterface.create().getProductList()
//
//        response.enqueue(object : Callback<ProductList> {
//            override fun onResponse(call: Call<ProductList>?, response: Response<ProductList>?) {
//                Log.d("test", "Результат ${response?.body()}")
//
//            }
//
//            override fun onFailure(call: Call<ProductList>, t: Throwable) {
//
//            }
//        })
//    }
}