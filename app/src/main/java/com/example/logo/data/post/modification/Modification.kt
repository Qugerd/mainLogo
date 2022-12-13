package com.example.logo.data.post.modification


import com.google.gson.annotations.SerializedName

data class Modification(
    @SerializedName("product_id")
    val productId: Int,
    @SerializedName("quantity")
    val quantity: Int
)