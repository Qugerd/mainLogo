package com.example.logo.data.modelProductList

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("id")
    val id: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("slug")
    val slug: String,

    @SerializedName("parent_id")
    val parent_id: String? = null
)