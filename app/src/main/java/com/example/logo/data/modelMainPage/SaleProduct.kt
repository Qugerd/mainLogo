package com.example.logo.data.modelMainPage


import com.google.gson.annotations.SerializedName

data class SaleProduct(
    @SerializedName("discount_price")
    val discountPrice: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("images")
    val images: List<Image>,
    @SerializedName("is_new")
    val isNew: Boolean,
    @SerializedName("is_sale")
    val isSale: Boolean,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("slug")
    val slug: String
)