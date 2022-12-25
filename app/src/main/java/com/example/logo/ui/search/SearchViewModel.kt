package com.example.logo.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.logo.Constant
import com.example.logo.api.RetrofitInstance
import com.example.logo.data.modelProductDetails.Category
import com.example.logo.data.modelProductList.DataProductList
import com.example.logo.data.modelProductList.ProductList
import com.example.logo.databinding.FragmentSearchBinding
import com.example.logo.source.DataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchViewModel : ViewModel() {

    private val dataRepository = DataRepository(RetrofitInstance.service)

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!


    private val _searchList = MutableLiveData<List<DataProductList>>()
    val searchList: LiveData<List<DataProductList>> = _searchList


//    private val _categoryInfo = MutableLiveData<com.example.logo.data.modelProductList.Category>()
//    val categoryInfo: LiveData<com.example.logo.data.modelProductList.Category> = _categoryInfo

    fun getSearchQuery(query : String) {
        try {
            viewModelScope.launch {
                val response = withContext(Dispatchers.IO) {
                    dataRepository.getSearchQuery(query)
                }
                Log.d("test", "response: $response")
                _searchList.postValue(response.data)
            }
        }
        catch (e: Exception){
            e.message
        }
    }

    fun getCategoryInfo(){
        try {
            viewModelScope.launch {
                val response = withContext(Dispatchers.IO) {
                    dataRepository.getProductList()
                }
                Log.d("test", "response: $response")
                _searchList.postValue(response.data)
            }
        }
        catch (e: Exception){
            e.message
        }
    }
}