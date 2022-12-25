package com.example.logo.data.modelCart


import com.google.gson.annotations.SerializedName

data class Brand(
    @SerializedName("brand_name")
    val brandName: String,
    @SerializedName("id")
    val id: Int
)