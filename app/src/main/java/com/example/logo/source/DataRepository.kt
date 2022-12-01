package com.example.logo.source


import com.example.logo.api.ApiGetFunction
import com.example.logo.data.modelCategoryList.Categor
import com.example.logo.data.modelMainPage.MainPageInfo
import com.example.logo.data.modelProductDetails.ProductDetails
import com.example.logo.data.modelProductList.ProductList

class DataRepository(private val apiGetFunction: ApiGetFunction) {

    suspend fun getProductList() : ProductList {
        return apiGetFunction.getProductList()
    }

    suspend fun getProductDetails(slug: String) : ProductDetails {
        return apiGetFunction.getProductDetails(slug)
    }

    suspend fun getCategoryList() : Categor{
        return apiGetFunction.getCategoryList()
    }

    suspend fun getMainPageInfo() : MainPageInfo {
        return apiGetFunction.getMainPageInfo()
    }
}