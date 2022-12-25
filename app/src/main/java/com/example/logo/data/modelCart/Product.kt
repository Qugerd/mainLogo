package com.example.logo.data.modelCart


import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("product_id")
    val productId: Int,
    @SerializedName("quantity")
    val quantity: Int
)