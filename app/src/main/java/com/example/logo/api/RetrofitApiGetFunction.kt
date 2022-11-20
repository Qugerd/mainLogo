package com.example.logo.api


import com.example.logo.data.modelProductList.ProductList
import retrofit2.http.GET

interface RetrofitApiGetFunction: ApiGetFunction {

    @GET("api/catalog/product/list")
    override suspend fun getProductList() : ProductList
}