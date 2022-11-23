package com.example.logo.data.modelProductDetails


import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("id")
    val id: String,
    @SerializedName("path")
    val path: String
)