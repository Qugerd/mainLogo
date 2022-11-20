package com.example.logo.data.modelProductList

import com.google.gson.annotations.SerializedName

data class Link(
    @SerializedName("url")
    val url: String,

    @SerializedName("label")
    val label: String,

    @SerializedName("active")
    val active: String
)