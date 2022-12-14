package com.example.logo.data.modelProductList

import com.google.gson.annotations.SerializedName

data class ProductList(
    @SerializedName("data")
    val data: List<DataProductList>,

    @SerializedName("links")
    val links: Links,

    @SerializedName("meta")
    val meta: Meta,

    @SerializedName("filters")
    val filters: List<Filter>,

    @SerializedName("category")
    val category: List<Category>
)