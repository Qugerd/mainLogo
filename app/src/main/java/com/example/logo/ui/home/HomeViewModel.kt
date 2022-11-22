package com.example.logo.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.logo.api.RetrofitInstance
import com.example.logo.data.modelProductList.ProductList
import com.example.logo.source.DataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(): ViewModel(){

    private val dataRepository = DataRepository(RetrofitInstance.service)

    private val _productListLiveData = MutableLiveData<ProductList>()
    val productListLiveData: LiveData<ProductList> = _productListLiveData

    fun getProductList() {
        viewModelScope.launch {
            val response = withContext(Dispatchers.IO) {
                dataRepository.getProductList()
            }

            _productListLiveData.postValue(response)
        }
    }

    fun getProductDetails() {
        viewModelScope.launch {
            val response = withContext(Dispatchers.IO) {
                dataRepository.getProductDetails()
            }
            Log.d("test", "О продукте: ${response}")

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