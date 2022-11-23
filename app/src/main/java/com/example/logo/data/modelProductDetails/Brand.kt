package com.example.logo.data.modelProductDetails


import com.google.gson.annotations.SerializedName

data class Brand(
    @SerializedName("brand_name")
    val brandName: String,
    @SerializedName("id")
    val id: String
)