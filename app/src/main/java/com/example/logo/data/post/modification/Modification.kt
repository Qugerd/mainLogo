package com.example.logo.data.post.modification


import com.google.gson.annotations.SerializedName

data class Modification(
    @SerializedName("product_id")
    val productId: Int,
    @SerializedName("color_id")
    val color_id: Int,
    @SerializedName("size_id")
    val size_id: Int,
    @SerializedName("quantity")
    val quantity: Int
)