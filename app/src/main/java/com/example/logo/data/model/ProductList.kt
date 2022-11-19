package com.example.logo.data.model

import com.google.gson.annotations.SerializedName

data class ProductList(
    @SerializedName("data")
    val data: List<Data>,

    @SerializedName("links")
    val links: Links,

    @SerializedName("meta")
    val meta: Meta,

    @SerializedName("filters")
    val filters: List<Filter>,

    @SerializedName("category")
    val category: List<Category>
)