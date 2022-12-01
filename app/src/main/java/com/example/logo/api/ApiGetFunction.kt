package com.example.logo.api

import com.example.logo.data.modelCategoryList.Categor
import com.example.logo.data.modelMainPage.MainPageInfo
import com.example.logo.data.modelProductDetails.ProductDetails
import com.example.logo.data.modelProductList.ProductList

interface ApiGetFunction {

    suspend fun getProductList() : ProductList

    suspend fun getProductDetails(slug: String): ProductDetails

    suspend fun getCategoryList() : Categor

    suspend fun getMainPageInfo() : MainPageInfo
}