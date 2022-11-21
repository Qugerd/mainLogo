package com.example.logo.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    var BASE_URL = "http://10.0.2.2:80/"
    private val retrofitBuilder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofitBuilder.create(RetrofitApiGetFunction::class.java)
}