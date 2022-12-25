package com.example.logo.data.modelCart


import com.google.gson.annotations.SerializedName

data class ImageX(
    @SerializedName("id")
    val id: Int,
    @SerializedName("path")
    val path: String
)