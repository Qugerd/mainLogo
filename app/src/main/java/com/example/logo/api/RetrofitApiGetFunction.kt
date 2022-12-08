package com.example.logo.api


import com.example.logo.data.modelCategoryList.Categor
import com.example.logo.data.modelMainPage.MainPageInfo
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

    // http://localhost/api/main_page
    @GET("api/main_page")
    override suspend fun getMainPageInfo(): MainPageInfo

    // http://localhost/api/catalog/product/list?category%20name=
    @GET("api/catalog/product/list")
    override suspend fun getCategoryProductList(@Query("category%20name") categoryName: String): ProductList

    // http://localhost/api/catalog/product/list?search_query=
    @GET("api/catalog/product/list")
    override suspend fun getSearchQuery(@Query("search_query") query: String): ProductList
}