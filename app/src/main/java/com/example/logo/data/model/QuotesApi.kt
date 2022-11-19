package com.example.logo.data.model

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface QuotesApi {
    @GET("api/categories/list")
    suspend fun getQuotes() : Response<ProductList>
}