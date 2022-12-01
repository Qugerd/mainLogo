package com.example.logo.ui.home

import android.content.Context
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.logo.Constant.TEST
import com.example.logo.Constant.print
import com.example.logo.api.RetrofitInstance
import com.example.logo.data.modelMainPage.MainPageInfo
import com.example.logo.data.modelProductDetails.Data
import com.example.logo.data.modelProductList.DataProductList
import com.example.logo.data.modelProductList.ProductList
import com.example.logo.databinding.FragmentHomeBinding
import com.example.logo.source.DataRepository
import com.example.logo.ui.home.adapters.NewClothesAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(): ViewModel(){

    private val dataRepository = DataRepository(RetrofitInstance.service)

    private val _productListNewClothesData = MutableLiveData<List<DataProductList>>()
    val productListNewClothesData: LiveData<List<DataProductList>> = _productListNewClothesData

    private val _productListSalesData = MutableLiveData<List<DataProductList>>()
    val productListSalesData: LiveData<List<DataProductList>> = _productListSalesData

    private val _productDetailsLiveData = MutableLiveData<Data>()
    val productDetailsLiveData: LiveData<Data> = _productDetailsLiveData

    private val _mainPageInfo= MutableLiveData<MainPageInfo>()
    val mainPageInfo: LiveData<MainPageInfo> = _mainPageInfo

    fun getMainPageInfo(){
        viewModelScope.launch {
            val response = withContext(Dispatchers.IO) {
                dataRepository.getMainPageInfo()
            }

            _mainPageInfo.postValue(response)
        }
    }

//    fun getProductList() {
//        viewModelScope.launch {
//            val response = withContext(Dispatchers.IO) {
//                dataRepository.getProductList()
//            }
//
//            getListNewProduct(response)
//            getListSalesProduct(response)
//        }
//    }

    private fun getListNewProduct(response: ProductList){
        val tmp: ArrayList<DataProductList> = ArrayList()

        response.data.forEach {
            if (it.is_new) tmp.add(it)
        }

        _productListNewClothesData.postValue(tmp)
    }

    private fun getListSalesProduct(response: ProductList){
        val tmp: ArrayList<DataProductList> = ArrayList()

        response.data.forEach{
            if (it.is_sale) tmp.add(it)
        }
        _productListSalesData.postValue(tmp)
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