package com.example.logo.data

import com.example.logo.Categor
import com.example.logo.data.model.ProductList
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiInterface {
    @GET("api/catalog/product/list")
    fun getProductList() : Call<ProductList>

    @GET("api/categories/list")
    fun getCatecoriesList() : Call<Categor>

    companion object {

        var BASE_URL = "http://10.0.2.2:80/"

        fun create() : ApiInterface {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}