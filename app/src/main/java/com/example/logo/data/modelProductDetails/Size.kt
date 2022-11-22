package com.example.logo.data.modelProductDetails


import com.google.gson.annotations.SerializedName

data class Size(
    @SerializedName("color_name")
    val colorName: String,
    @SerializedName("id")
    val id: String
)