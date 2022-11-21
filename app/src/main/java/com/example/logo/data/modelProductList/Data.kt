package com.example.logo.data.modelProductList

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("id")
    val id: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("slug")
    val slug: String,

    @SerializedName("price")
    val price: String,

    @SerializedName("discount_price")
    val discount_price: String,

    @SerializedName("is_sale")
    val is_sale: Boolean,

    @SerializedName("is_new")
    val is_new: Boolean,

    @SerializedName("images")
    val images: List<Images>
)