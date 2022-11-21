package com.example.logo.source

import com.example.logo.api.ApiGetFunction
import com.example.logo.data.modelProductList.ProductList

class DataRepository(private val apiGetFunction: ApiGetFunction) {

    suspend fun getProductList() : ProductList {
        return apiGetFunction.getProductList()
    }
}