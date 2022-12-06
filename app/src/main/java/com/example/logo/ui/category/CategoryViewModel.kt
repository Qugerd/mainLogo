package com.example.logo.ui.category

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.logo.api.RetrofitInstance
import com.example.logo.data.modelProductDetails.Data
import com.example.logo.data.modelProductList.DataProductList
import com.example.logo.data.modelProductList.ProductList
import com.example.logo.source.DataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CategoryViewModel: ViewModel() {

    private val dataRepository = DataRepository(RetrofitInstance.service)

    private val _categoryProductData = MutableLiveData<List<DataProductList>>()
    val categoryProductData: LiveData<List<DataProductList>> = _categoryProductData


    fun getProductDetails() {
        try {
            viewModelScope.launch {
                val response = withContext(Dispatchers.IO) {
                    dataRepository.getProductList()
                }
                Log.d("test", "О продукте: ${response.data}")
                _categoryProductData.postValue(response.data)
            }
        }
        catch (e: Exception){
            e.printStackTrace()
        }
    }
}