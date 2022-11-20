package com.example.logo.data.modelProductList

import com.google.gson.annotations.SerializedName

data class Links(
    @SerializedName("first")
    val first: String,

    @SerializedName("last")
    val last: String,

    @SerializedName("prev")
    val prev: String,

    @SerializedName("next")
    val next: String
)