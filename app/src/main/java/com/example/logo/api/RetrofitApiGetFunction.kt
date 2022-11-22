package com.example.logo.api


import com.example.logo.data.modelCategoryList.Categor
import com.example.logo.data.modelProductDetails.ProductDetails
import com.example.logo.data.modelProductList.ProductList
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApiGetFunction: ApiGetFunction{

    // http://10.0.2.2:80/api/catalog/product/list
    @GET("api/catalog/product/list")
    override suspend fun getProductList() : ProductList

    // http://10.0.2.2:80/api/catalog/product/details?slug=
    @GET("api/catalog/product/details")
    override suspend fun getProductDetails(@Query("slug") slug: String): ProductDetails

    // http://10.0.2.2:80/api/categories/list
    @GET("api/categories/list")
    override suspend fun getCategoryList(): Categor
}