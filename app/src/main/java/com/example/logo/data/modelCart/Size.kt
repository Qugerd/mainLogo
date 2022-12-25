package com.example.logo.data.modelCart


import com.google.gson.annotations.SerializedName

data class Size(
    @SerializedName("id")
    val id: Int,
    @SerializedName("size_name")
    val sizeName: String
)