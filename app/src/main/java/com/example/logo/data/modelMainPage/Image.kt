package com.example.logo.data.modelMainPage


import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("id")
    val id: String,
    @SerializedName("path")
    val path: String
)