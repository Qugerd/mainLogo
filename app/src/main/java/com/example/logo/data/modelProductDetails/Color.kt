package com.example.logo.data.modelProductDetails


import com.google.gson.annotations.SerializedName

data class Color(
    @SerializedName("color_name")
    val colorName: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("hex_color")
    val hex_color: String
)