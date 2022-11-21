package com.example.logo.api

import com.example.logo.data.modelProductList.ProductList

interface ApiGetFunction {

    suspend fun getProductList() : ProductList
}