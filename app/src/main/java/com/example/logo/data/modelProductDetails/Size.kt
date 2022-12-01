package com.example.logo.data.modelProductDetails


import com.google.gson.annotations.SerializedName

data class Size(
    @SerializedName("size_name")
    val sizeName: String,
    @SerializedName("id")
    val id: String
)