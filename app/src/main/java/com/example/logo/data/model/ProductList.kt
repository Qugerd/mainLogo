package com.example.logo.data.model

import com.google.gson.annotations.SerializedName

data class ProductList(
    @SerializedName("data")
    val data: ArrayList<Data> = arrayListOf(),

    @SerializedName("links")
    val links: Links? = null,

    @SerializedName("meta")
    val meta: Meta? = null,

    @SerializedName("filters")
    val filters: ArrayList<Filter> = arrayListOf(),

    @SerializedName("category")
    val category: ArrayList<Category> = arrayListOf()
)