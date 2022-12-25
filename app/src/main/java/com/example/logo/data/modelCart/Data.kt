package com.example.logo.data.modelCart


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("items")
    val items: List<Item>,
    @SerializedName("price_total")
    val priceTotal: Double
)