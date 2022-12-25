package com.example.logo.data.modelCart


import com.google.gson.annotations.SerializedName

data class Color(
    @SerializedName("color_name")
    val colorName: String,
    @SerializedName("id")
    val id: Int
)