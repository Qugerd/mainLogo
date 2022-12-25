package com.example.logo.data.modelCart


import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("price_item")
    val priceItem: String,
    @SerializedName("price_total")
    val priceTotal: String,
    @SerializedName("product")
    val product: ProductX,
    @SerializedName("quantity")
    val quantity: Int
)