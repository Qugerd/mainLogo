package com.example.logo.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.logo.api.RetrofitInstance
import com.example.logo.data.modelProductDetails.Data
import com.example.logo.data.modelProductDetails.ProductDetails
import com.example.logo.data.modelProductList.ProductList
import com.example.logo.source.DataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(): ViewModel(){

    private val dataRepository = DataRepository(RetrofitInstance.service)

    private val _productListLiveData = MutableLiveData<ProductList>()
    val productListLiveData: LiveData<ProductList> = _productListLiveData

    private val _productDetailsLiveData = MutableLiveData<Data>()
    val productDetailsLiveData: LiveData<Data> = _productDetailsLiveData

    fun getProductList() {
        viewModelScope.launch {
            val response = withContext(Dispatchers.IO) {
                dataRepository.getProductList()
            }

            _productListLiveData.postValue(response)
        }
    }

    fun getProductDetails(slug: String) {
        viewModelScope.launch {
            val response = withContext(Dispatchers.IO) {
                dataRepository.getProductDetails(slug)
            }
            Log.d("test", "О продукте: ${response.data}")
            _productDetailsLiveData.postValue(response.data)
        }
    }

    fun getCategoryList() {
        viewModelScope.launch {
            val response = withContext(Dispatchers.IO) {
                dataRepository.getCategoryList()
            }
            Log.d("test", "Категория: ${response}")
        }
    }
}